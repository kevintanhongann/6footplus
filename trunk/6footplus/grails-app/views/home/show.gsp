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
            <div style="float:left">
                <g:if test="${after}">
                    « <g:link controller="home" action="show" params="[id:after.id]">${after.subject?.encodeAsHTML()}</g:link>
                </g:if>
            </div>
            <div style="float:right">
                <g:if test="${before}">
                    <g:link controller="home" action="show" params="[id:before.id]">${before.subject?.encodeAsHTML()}</g:link> »
                </g:if>
            </div>
            <div style="clear: both; padding-top:30px"/>
            <span class="heading">${article?.subject?.encodeAsHTML()}</span>
            <span class="date">(<g:formatDate format="dd MMM yyyy" date="${article.dateCreated}"/>)
                <g:if test="${article.tags}"> - tagged as
                    <g:set var="counter" value="${1}" />
                    <g:each in="${article.tags}">
                        <g:set var="tag" value="${it}" />
                        <g:link action="index" params="[tag:it]" class="brown_s">${tag}</g:link>${counter < article.tags.size ? ', ' : ''}
                        <g:set var="counter" value="${counter + 1}" />
                    </g:each>
                </g:if>
            </span>
            <div style="margin-top:10px;">${article?.body}</div>
            <div style="clear:both; padding-top: 10px; padding-bottom: 10px">
                <g:each var="link" in="${article?.links}">• <a class="brown" href="${link?.url}">${link?.label}</a>&nbsp;</g:each>
            </div>
            <div style="clear:both; margin-bottom:100px" id="comment_area_${article?.id}">
                <g:render template="comments" model="[article:article]" />
            </div>
        </g:if>
    </body>
</html>