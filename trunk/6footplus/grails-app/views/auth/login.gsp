<html>
<head>
    <meta name="layout" content="simple"/>
</head>
<body>

<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<div style="height: 20px">&nbsp;</div>

<g:form action="signIn">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <label style="width:120px" for="username">Username:</label><input type="text" name="username" value="${username}" /><br />
    <label style="width:120px" for="password">Password:</label><input type="password" name="password" value="" /><br />
    <label style="width:120px" for="rememberMe">Remember me?:</label><g:checkBox name="rememberMe" value="${rememberMe}" /><br/>
    <div style="margin-left:40px"><input type="submit" name="submitbutton" id="submitbutton" value="login" /></div>
</g:form>
</body>
</html>