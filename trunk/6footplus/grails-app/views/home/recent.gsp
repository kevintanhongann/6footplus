<g:each var="item" in="${articles}">
    <span style="padding-left: 20px">• <a href="/home/show/${item.id}">${(item.subject.length() > 13) ? item.subject.substring(0, 13) + "...": item.subject}</a></span><br />
</g:each>
