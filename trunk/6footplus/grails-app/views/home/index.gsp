<html>
    <head>
        <meta name="layout" content="main"/>
    </head>
    <body>

        <g:include controller="twitter" action="show"/>

        <g:each var="article" in="${articles}">
            <div>
                <g:link class="title" action="show" id="${article.id}">${article.subject?.encodeAsHTML()}</g:link>
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
                <div style="margin-top:10px;">${article.body}</div>
                <div style="clear:both; padding-top: 10px; padding-bottom: 10px">
                    <g:each var="link" in="${article.links}">• <a class="brown" href="${link.url}">${link.label}</a>&nbsp;</g:each>
                </div>
                <div id="comment_area_${article.id}" style="clear:both;">
                    <g:render template="comments" model="[article:article]" />
                </div>
            </div>
            <div class="divider">&nbsp;</div>
        </g:each>

        <div class="paginateButtons">
            <g:paginate params="${params}" next="Previous" prev="Next" maxsteps="2" total="${blogCount}"/>
        </div>
        
    </body>
</html>