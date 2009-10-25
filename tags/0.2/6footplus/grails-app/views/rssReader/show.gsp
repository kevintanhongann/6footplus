<g:each var="item" in="${feedList}">
    <div style="padding-left: 20px;">
        <g:if test="${item.producer =='flickr'}">
            <a href="http://photos.6footplus.com" title="${item.title}"><img height="75" src="${item.thumbnail}" border="1" style="margin-top:10px; border-style: solid; border-color: rgb(102, 102, 51);"/></a>
        </g:if>
        <g:else>
            <a href="${item.link}" title="${item.title}"><img height="75" src="${item.thumbnail}" border="1" style="margin-top:10px; border-style: solid; border-color: rgb(102, 102, 51);"/></a>
        </g:else>
    </div>
</g:each>

