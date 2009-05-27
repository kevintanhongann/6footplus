<html>
<head>
    <meta name="layout" content="simple"/>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:link class="title" action="edit" id="${article.id}">${article.subject?.encodeAsHTML()}</g:link> <span class="date">(<g:formatDate format="dd MMM yyyy" date="${article.dateCreated}"/>)</span> <g:link class="light" action="delete" id="${article.id}">delete</g:link>
    <div style="margin-top:10px;">${article.body}</div>
    <div style="clear:both; padding-top: 10px; padding-bottom: 10px">
        <g:each var="link" in="${article.links}">• <a class="brown" href="${link.url}">${link.label}</a>&nbsp;</g:each> 
    </div>
</body>
</html>