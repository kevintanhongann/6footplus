<g:if test="${status != null}">
    <div style="padding: 20px">
        <div style="float:left; width:20px; padding-right: 10px; padding-left: 20px"><img src="/images/quote_left.png"/></div>
        <div style="float:left;width:80%; font-style:italic; font-size: 13px; font-family:Georgia"><twitter:markup text='${status.text}'/> <span class="date">(my tweet on the <g:formatDate format="dd MMM yyyy @ HH:mm, z" date="${status.createdAt}"/>)</span></div>
        <div style="clear:both"></div>
    </div>    
</g:if>
