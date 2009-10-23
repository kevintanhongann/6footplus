<html>
    <head>
        <meta name="layout" content="simple"/>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${article}">
            <div class="errors">
                <g:renderErrors bean="${article}" as="list"/>
            </div>
        </g:hasErrors>
        <g:form action="save" method="post">
            <label for='subject'>Subject:</label>
            <input type="text" size="74" id='subject' name='subject' value=""/><br/>
            <label for='body'>Body:</label>
            <textarea style="width: 550px; height: 400px" name='body'></textarea><br/>
            <label for='body'>Teaser:</label>
            <textarea style="width: 550px; height: 200px" name='body'></textarea><br/>
            <label for='category'>Author:</label>
            <g:select from="${users}" optionKey="id" optionValue="username" name='author.id' value=""/><br/>
            <label for='category'>Status:</label>
            <g:select from="${ArticleStatus.values()}" name="status" value=""/><br/>
            <label for='createdDate'>Created:</label>
            <g:datePicker name='dateCreated' value=""/><br/>
            <label for='lastUpdated'>Udpate:</label>
            <g:datePicker name='lastUpdated' value=""></g:datePicker><br/><br/>
            <label for='lastUpdated'><g:actionSubmit style="margin-left: 80px" value="Save"/></label>
        </g:form>
    </body>
</html>


