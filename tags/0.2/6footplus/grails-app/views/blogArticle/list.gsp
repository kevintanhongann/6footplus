<html>
<head>
    <meta name="layout" content="simple"/>
</head>
<body>
    <g:link action="create">create an article</g:link><br/><br/>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:each var="article" in="${articles}">
        <g:link class="title" action="show" id="${article.id}">${article.subject?.encodeAsHTML()}</g:link>
        <span class="date">(<g:formatDate format="dd MMM yyyy" date="${article.dateCreated}"/>) - ${article.status}</span>
        <g:link class="light" action="edit" id="${article.id}">edit</g:link> |
        <g:link class="light" action="delete" id="${article.id}">delete</g:link> |
        <g:link class="light" action="show" id="${article.id}">tags</g:link>
        <div style="margin-top:10px;">${article.body}</div>
        <div style="clear:both; padding-top: 10px; padding-bottom: 10px">
            <g:each var="link" in="${article.links}">• <a class="brown" href="${link.url}">${link.label}</a>&nbsp;</g:each> 
        </div>
        <div class="divider"></div>
    </g:each>
    <div class="paginateButtons">
        <g:paginate maxsteps="1" total="${blogCount}"/>
    </div>
</body>
</html>