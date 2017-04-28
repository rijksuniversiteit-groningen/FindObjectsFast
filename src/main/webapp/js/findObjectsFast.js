function FindObjectsFast() {
    var searchTermInput = $('fof_searchTerm');
    var searchUrl = '';
    var searchTerm = '';
    var storedSearchResult = [];
    var highlightedSearchResult = null;
    var delayObject = {};
    searchTermInput.addEventListener('keyup', pressedSearchTermKey);

    this.setSearchUrl = function(newSearchUrl) {
        searchUrl = newSearchUrl;
    };

    function setSearchTerm(newSearchTerm) {
        searchTerm = newSearchTerm;
    }

    function pressedSearchTermKey(e) {
        if(e.which == 13) {
            if(highlightedSearchResult) {
                console.log('going to' + JSON.stringify(highlightedSearchResult));
                window.location.href = highlightedSearchResult.url;
            }
            e.preventDefault();
        }
        else if(e.which == 38) {
            cycleSearchResult(-1);
        }
        else if(e.which == 40) {
            cycleSearchResult(1);
        }
        else {
            search();
        }
    }

    function flattenSearchResults(searchResult) {
        return searchResult
                .map(function (section) {
                    return section.results;
                })
                .reduce(function (a, b) {
                    return a.concat(b);
                }, []);
    }

    function cycleSearchResult(direction) {
        var flattenedSearchResult = flattenSearchResults(storedSearchResult);

        var selectedIndex = 0;
        flattenedSearchResult.forEach(function(enrollment, index) {
            if(enrollment == highlightedSearchResult) {
                selectedIndex = index;
            }
        });
        selectedIndex = (selectedIndex + flattenedSearchResult.length + direction) % flattenedSearchResult.length;
        highlightedSearchResult = flattenedSearchResult[selectedIndex];
        renderSearchResults();
    }

    function search() {
        var searchTerm = $('fof_searchTerm').value;
        setSearchTerm(searchTerm);
        if(searchTerm.length >= 4) {
            delay(function() { searchInBlackboard(searchTerm); }, delayObject, 400);
        } else {
            receiveSearchResults({userResultList: [], courseResultList: [], organizationResultList: []});
        }
    }

    function delay(func, delayObject, timeout) {
        if(delayObject.timer) {
            window.clearTimeout(delayObject.timer);
        }
        delayObject.timer = window.setTimeout(func, timeout);
    }

    function searchInBlackboard(searchTerm) {
        var url = searchUrl + '?searchTerm=' + encodeURIComponent(searchTerm);
        new Ajax.Request(url, {
            method: 'get',
            onSuccess: function (response) {
                receiveSearchResults(response.responseJSON);
            }
        });
    }

    function receiveSearchResults(searchResult) {
        storedSearchResult = [
            {label: 'Users', results: searchResult.userResultList},
            {label: 'Courses', results: searchResult.courseResultList, type: 'course'},
            {label: 'Organizations', results: searchResult.organizationResultList, type: 'course'}
        ];
        highlightedSearchResult = flattenSearchResults(storedSearchResult)[0];
        renderSearchResults();
    }

    function renderSearchResults() {
        renderGroupedSearchResults(storedSearchResult);
    }

    function renderGroupedSearchResults(groupedResults) {
        var divElement = $('fof_searchResults');
        divElement.innerHTML = '';

        var children = div({},
            groupedResults
                .filter(function (result) {
                    return result.results.length > 0
                })
                .map(function (result) {
                    var header = div({'class': 'fof_search_header'}, text(result.label));
                    var ulElement = ul({'class': 'fof_list'},
                    result.results
                        .map(function (enrollment) {
                            var item = result.type == 'course' ? renderCourse(enrollment) : renderUser(enrollment);
                            if (highlightedSearchResult == enrollment) {
                                item.addClassName('fof_highlightedSearchResult');
                            }
                            highlightSearchInNode(item, searchTerm);
                            return item;
                        }));
                    return div({}, [header, ulElement]);
                }));
        divElement.appendChild(children);
    }

    function renderCourse(result) {
        var link = a({'href': result.url}, text(result.title));
        var item = li({'title': result.code}, [
            span({'class': result.course ? 'icon icon-puzzle-piece' : 'icon-group', title: 'Course'}),
            text(' '),
            renderCourseCode(result.code),
            link]);
        if (!result.available) {
            link.className = 'fof_unavailable';
            item.appendChild(text(' (unavailable)'));
        }
        return item;
    }

    function renderUser(result) {
        var emailPart = result.email ? ' (' + result.email + ')' : '';
        var link = a({'href': result.url}, text(result.displayName));
        var item = li({'title': result.username}, [
            span({'class': 'icon icon-user', title: 'instructor'}),
            text(' '),
            renderCourseCode(result.username + emailPart),
            link,
            text(' | '),
            a({href: result.courseEnrollmentsUrl}, text('Courses')),
            text(' | '),
            a({href: result.organizationEnrollmentsUrl}, text('Organizations')),
            text(' | '),
            a({href: result.editPasswordUrl}, text('Password'))]);

        if (!result.available) {
            link.className = 'fof_unavailable';
            item.appendChild(text(' (unavailable)'));
        }
        return item;
    }

    function renderCourseCode(code) {
        return div({'class': 'fof_courseCode'}, html(' ' + code + '<br/>'));
    }

    function highlightSearchInHTML(html, searchTerm) {
        var htmlLowercase = html.toLowerCase();
        var position = htmlLowercase.indexOf(searchTerm);
        if (position >= 0) {
            return html.substring(0, position) + '<span class="fof_searchHighlight">'
                + html.substr(position, searchTerm.length) + '</span>'
                + highlightSearchInHTML(html.substr(position + searchTerm.length), searchTerm);
        } else {
            return html;
        }
    }

    function highlightSearchInNode(node, searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        if(node.nodeType == 3) {
            var html = highlightSearchInHTML(node.nodeValue, searchTerm);
            var spanElement = span();
            spanElement.innerHTML = html;
            node.parentNode.replaceChild(spanElement, node);
        } else {
            for(var i = 0; i < node.childNodes.length; i++) {
                highlightSearchInNode(node.childNodes[i], searchTerm);
            }
        }
    }

    var domBuilder = new DOMBuilder();
    var div = domBuilder.createElementBuilder('div');
    var span = domBuilder.createElementBuilder('span');
    var ul = domBuilder.createElementBuilder('ul');
    var li = domBuilder.createElementBuilder('li');
    var a = domBuilder.createElementBuilder('a');
    var img = domBuilder.createElementBuilder('img');
    var p = domBuilder.createElementBuilder('p');
    var text = domBuilder.createTextNodeBuilder();
    var html = domBuilder.createHtmlNodeBuilder();
};