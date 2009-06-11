    <g:if test="${more}">
        <script type="text/javascript">$('twitter_links').hide();</script>
        <g:each var="item" in="${more}">
            <p style="padding-left: 20px">• <twitter:markup text='${item.text}'/> <span class="date">(<g:formatDate format="dd MMM yyyy" date="${item.createdAt}"/>)</span></p>
        </g:each>
        <p style="padding-left: 20px">• <a href="#" onclick="Effect.BlindUp('twitter_more'); $('twitter_links').appear({ duration: 1.5 }); return false;">display less twitter messages</a>&nbsp;&nbsp;&nbsp;• <twitter:userLink name="6footplus" text="follow me on twitter"/></p>
    </g:if>

