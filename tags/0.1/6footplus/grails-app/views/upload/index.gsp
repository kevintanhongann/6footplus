<html>
<head>
    <meta name="layout" content="simple"/>
</head>
<body>

<g:if test="${flash.message}"><div class="message">${flash.message}</div></g:if>

<g:form action="upload" method="post" enctype="multipart/form-data">
	<input type="file" name="myFile" style="color: #333300;"/>
	<input type="submit" value="Submit"/>
</g:form>

</body>
</html>