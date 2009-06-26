<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>

<g:include controller="twitter" action="show"/>

<g:each var="article" in="${articles}">
    <div>
        <g:link class="title" action="show" id="${article.id}">${article.subject?.encodeAsHTML()}</g:link>
        <span class="date">(<g:formatDate format="dd MMM yyyy" date="${article.dateCreated}"/>)</span>
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
    <g:paginate next="Previous" prev="Next" maxsteps="2" max="1" total="${blogCount}"/>
</div>

</body>
</html>