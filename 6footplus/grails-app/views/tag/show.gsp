<g:set var="counter" value="${1}" />
<g:each in="${tags}" var="entry">
    <g:link controller="home" action="index" params="[tag:entry.key]" class="light">${entry.key}</g:link><span class="date"> (${entry.value})</span>${counter < tags.size() ? ', ' : ''}
    <g:set var="counter" value="${counter + 1}" />
</g:each>
<g:if test="${tags.size() == 0}">none</g:if>
<g:else>
     ...<g:link controller="tag" action="cloud">view</g:link> tag cloud
</g:else>
