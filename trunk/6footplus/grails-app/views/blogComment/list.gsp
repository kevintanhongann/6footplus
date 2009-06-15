<html>
    <head>
        <meta name="layout" content="simple"/>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        Sort by : <g:sortableColumn property="dateCreated" title="Created" />, <g:sortableColumn property="author" title="Author" /> or <g:sortableColumn property="id" title="ID" /><br><br>

        <g:each var="comment" in="${comments}">
            <g:link class="light" action="edit" id="${comment.id}">edit</g:link> |
            <g:link class="light" action="delete" id="${comment.id}">delete</g:link> | 
            [${comment.id}] ${comment.message} (by ${comment.author} on <g:formatDate format="dd MMM yyyy, HH:mm" date="${comment.dateCreated}"/> for article <g:link class="brown" controller="blogArticle" action="show" id="${comment.article.id}">'${comment.article.subject}'</g:link>)
            <div style="margin-top: 10px" class="divider"></div>
        </g:each>
        <div class="paginateButtons">
            <g:paginate maxsteps="1" total="${commentCount}"/>
        </div>
    </body>
</html>