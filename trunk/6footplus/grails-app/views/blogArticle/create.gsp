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
    <table>
        <tr>
            <td valign='top'>
                <label for='subject'>Subject:</label>
            </td>
            <td valign='top'>
                <input type="text" size="74" id='subject' name='subject' value=""/>
            </td>
        </tr>
        <tr>
            <td valign='top'>
                <label for='body'>Body:</label>
            </td>
            <td valign='top'>
                <textarea rows='10' cols='80' name='body'></textarea>
            </td>
        </tr>
        <tr>
            <td valign='top'>
                <label for='category'>Author:</label>
            </td>
            <td valign='top'>
                <g:select from="${users}" optionKey="id" optionValue="username" name='author.id' value=""/>
            </td>
        </tr>
        <tr>
            <td valign='top'>
                <label for='category'>Status:</label>
            </td>
            <td valign='top'>
                <g:select from="${ArticleStatus.values()}" name="status" value=""/>
            </td>
        </tr>
        <tr>
            <td valign='top'>
                <label for='createdDate'>Created:</label>
            </td>
            <td valign='top'>
                <g:datePicker name='dateCreated' value=""/>
            </td>
        </tr>
        <tr>
            <td valign='top'>
                <label for='lastUpdated'>Udpate:</label>
            </td>
            <td valign='top'>
                <g:datePicker name='lastUpdated' value=""></g:datePicker>
            </td>
        </tr>
        <tr>
            <td valign='top'>
                <label for='lastUpdated'><g:actionSubmit class="save" value="Save"/></label>
            </td>
        </tr>
    </table>
</g:form>
</body>
</html>


