<g:set var="body" value="${article?.body}"/>
<g:set var="teaser" value="${article?.teaser}"/>

<div style="margin-top:10px;">
    <g:if test="${teaser}">
        ${teaser}...[<g:link action="show" id="${article.id}">read more</g:link>]
    </g:if>
    <g:else>${body}</g:else>
</div>