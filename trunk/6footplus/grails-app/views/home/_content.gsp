<g:set var="body" value="${article?.body}"/>
<g:set var="teaser" value="${article?.teaser}"/>

<div style="margin-top:10px;">
    <g:if test="${teaser}">
        ${teaser}
        <div style="padding-top: 10px">• <g:link action="show" id="${article.id}">read more</g:link> »</div>
    </g:if>
    <g:else>${body}</g:else>
</div>