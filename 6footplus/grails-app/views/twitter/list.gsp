<g:each var="item" in="${result}">
    <p style="padding-left: 20px">• <twitter:markup text='${item.text}'/> <span class="date">(<g:formatDate format="dd MMM yyyy @ HH:mm" date="${item.createdAt}"/>)</span></p>
</g:each>
