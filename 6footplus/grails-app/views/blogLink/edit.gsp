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
        <g:form controller="blogLink" method="post">
            <input type="hidden" name="id" value="${link?.id}"/>
            <label>Label:</label><input type="text" id='label' name='label' value="${link?.label?.encodeAsHTML()}"/><br/>
            <label>URL:</label><input type="text" id='url' name='url' value="${link?.url}"/><Br/>
            <label>Article:</label><g:select optionKey="id" from="${articles}" name='article.id' value="${link?.article?.id}" ></g:select>
            <div style="margin-left: 81px; margin-top: 20px"><g:actionSubmit class="submitbutton" value="Update"/></div>
            <div style="margin-left: 81px"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete"/></div>
        </g:form>
    </body>
</html>


