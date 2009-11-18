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
        <span class="date">
            <g:if test="${article.tags}">(
                <g:set var="counter" value="${1}" />
                <g:each in="${article.tags}">
                    <g:set var="tag" value="${it}" />
                    <g:link action="index" params="[tag:it]" class="brown_s">${tag}</g:link>${counter < article.tags.size ? ', ' : ''}
                    <g:set var="counter" value="${counter + 1}" />
                </g:each>)
            </g:if>
        </span>
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