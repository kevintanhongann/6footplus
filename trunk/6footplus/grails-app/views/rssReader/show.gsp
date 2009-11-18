<g:each var="item" in="${feedList}">
    <div style="padding-left: 20px;">
        <tooltip:tip value="${item.title}">
            <g:if test="${item.producer =='flickr'}">
                <a href="http://photos.6footplus.com"><img height="75" src="${item.thumbnail}" border="1" style="margin-top:10px; border-style: solid; border-color: rgb(102, 102, 51);"/></a>
            </g:if>
            <g:else>
                <a href="${item.link}"><img height="75" src="${item.thumbnail}" border="1" style="margin-top:10px; border-style: solid; border-color: rgb(102, 102, 51);"/></a>
            </g:else>
        </tooltip:tip>
    </div>
</g:each>

