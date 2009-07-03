<html>
    <head>
        <meta name="layout" content="simple"/>
        <title>6footplus.com <g:if test="${article}">- ${article.subject?.encodeAsHTML()}</g:if></title>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <g:if test="${article}">
            <span class="heading">${article?.subject?.encodeAsHTML()}</span> <span class="date"> (<g:formatDate format="dd MMM yyyy" date="${article?.dateCreated}"/>)</span>
            <div style="margin-top:10px;">${article?.body}</div>
            <div style="clear:both; padding-top: 10px; padding-bottom: 10px">
                <g:each var="link" in="${article?.links}">• <a class="brown" href="${link?.url}">${link?.label}</a>&nbsp;</g:each>
            </div>
            <div style="clear:both" id="comment_area_${article?.id}">
                <g:render template="comments" model="[article:article]" />
            </div>
        </g:if>
    </body>
</html>