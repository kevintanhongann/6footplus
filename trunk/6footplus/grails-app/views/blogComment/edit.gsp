<html>
    <head>
        <meta name="layout" content="simple"/>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${comment}">
            <div class="errors">
                <g:renderErrors bean="${comment}" as="list"/>
            </div>
        </g:hasErrors>
        <g:form controller="blogComment" method="post">
            <input type="hidden" name="id" value="${comment?.id}"/>
            <label>Author:</label><input type="text" id='author' name='author' value="${comment?.author}"/><br/>
            <label>IP:</label><input type="text" id='ip' name='ip' value="${comment?.ip}"/><Br/>
            <label>Message:</label><input type="text" id='message' name='message' value="${comment?.message}"/><Br/>
            <label>Article:</label><g:select style="margin-bottom: 5px" optionKey="id" from="${articles}" name='article.id' value="${comment?.article?.id}" optionValue="subject"></g:select><br/>
            <label>Created:</label><g:datePicker name='dateCreated' value="${comment?.dateCreated}"></g:datePicker><br />
            <div style="margin-left: 81px; margin-top: 20px"><g:actionSubmit class="submitbutton" value="Update"/></div>
            <div style="margin-left: 81px"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete"/></div>
        </g:form>
    </body>
</html>


