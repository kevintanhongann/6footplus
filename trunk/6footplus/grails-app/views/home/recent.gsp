<g:each var="item" in="${articles}">
    <span style="padding-left: 20px">• <a title="${item.subject}" href="/home/show/${item.id}">${(item.subject.length() > 35) ? item.subject.substring(0, 35) + "...": item.subject}</a></span><br />
</g:each>
