<html>
    <head>
        <meta name="layout" content="simple"/>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${link}">
            <div class="errors">
                <g:renderErrors bean="${link}" as="list"/>
            </div>
        </g:hasErrors>
        <g:link action="edit" id="${link.id}">edit</g:link> | <g:link action="delete" id="${link.id}">delete</g:link> | <g:link action="create">create</g:link><br/><br/>
        <label>Label:</label> ${link?.label?.encodeAsHTML()}<br/>
        <label>URL:</label>${link?.url}<br/>
        <label>Article:</label><g:link controller="blogArticle" action="show" id="${link.article.id}">${link?.article.subject}</g:link><br/>
    </body>
</html>


