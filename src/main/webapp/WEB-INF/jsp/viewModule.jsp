<%@ taglib prefix="ng" uri="/bbNG" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id="contextPath" type="java.lang.String" scope="request"/>


<ng:jsFile href="${contextPath}/js/domBuilder.js"/>
<ng:jsFile href="${contextPath}/js/findObjectsFast.js"/>
<ng:cssFile href="${contextPath}/css/findObjectsFast.css"/>

<ng:includedPage ctxId="ctx">
    <form>
        <input type="text"
               name="searchTerm"
               id="fof_searchTerm"
               autocomplete="off"
               placeholder="Type to search users, courses and organizations"
        />
    </form>

    <div id="fof_searchResults"></div>

    <ng:jsOnLoadBlock>
        var findObjectsFast = new FindObjectsFast();
        findObjectsFast.setSearchUrl('${contextPath}/search');
    </ng:jsOnLoadBlock>
</ng:includedPage>