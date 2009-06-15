<html>
    <head>
        <meta name="layout" content="simple"/>
    </head>
    <body>
        <g:link action="create">create a link</g:link><br/><br/>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        Sort by : <g:sortableColumn property="id" title="ID" />, <g:sortableColumn property="url" title="URL" /><br><br>

        <g:each var="link" in="${links}">
            <g:link class="light" action="edit" id="${link.id}">edit</g:link> |
            <g:link class="light" action="delete" id="${link.id}">delete</g:link> |
            [${link.id}] "${link.label}" for article "${link.article.subject}" [<g:link class="light" controller="blogArticle" action="show" id="${link.article.id}">${link.article.id}</g:link>] (<a class="brown" href="${link.url}">${link.url}</a>)
            <div style="margin-top: 10px" class="divider"></div>
        </g:each>
        <div class="paginateButtons">
            <g:paginate maxsteps="1" total="${linkCount}"/>
        </div>
    </body>
</html>