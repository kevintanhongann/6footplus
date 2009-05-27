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
<g:form controller="blogArticle" method="post">
    <input type="hidden" name="id" value="${article?.id}"/>
    <label for='subject'>Subject:</label><input type="text" id='subject' name='subject' value="${article?.subject?.encodeAsHTML()}"/>
    <label for='body'>Body:</label><textarea rows='10' name='body'>${article?.body?.encodeAsHTML()}</textarea><br />
    <label for='category'>Status:</label><g:select from="${ArticleStatus.values()}" value="${article?.status}" name="status"/><br />
    <label for='createdDate'>Created:</label><g:datePicker name='dateCreated' value="${article?.dateCreated}"></g:datePicker><br />
    <label for='lastUpdated'>Updated:</label><g:datePicker name='lastUpdated' value="${article?.lastUpdated}"></g:datePicker><br />
    <div style="margin-left: 81px; margin-top: 20px"><g:actionSubmit class="submitbutton" value="Update"/></div>
    <div style="margin-left: 81px"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete"/></div>
</g:form>
</body>
</html>


