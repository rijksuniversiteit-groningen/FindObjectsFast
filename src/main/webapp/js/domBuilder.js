
var DOMBuilder = function() {
    this.createElementBuilder = function (tagName) {
        return function (attributes) {
            var element = document.createElement(tagName);
            addAttributes(element, attributes);
            Array.prototype.slice.apply(arguments, [1]).forEach(function (argument) {
                if (argument instanceof Array) {
                    argument.forEach(function (child) { element.appendChild(child); })
                } else {
                    element.appendChild(argument);
                }
            });
            return element;
        };
    };

    this.createHtmlNodeBuilder = function () {
        return function (text) {
            var element = document.createElement('span');
            element.innerHTML = text;
            return element;
        }
    };

    this.createTextNodeBuilder = function () {
        return function (text) {
            return document.createTextNode(text);
        }
    };

    function addAttributes(element, attributes) {
        if (attributes) {
            for (var attribute in attributes) {
                if (attributes.hasOwnProperty(attribute)) {
                    element.setAttribute(attribute, attributes[attribute]);
                }
            }
        }
    }

};