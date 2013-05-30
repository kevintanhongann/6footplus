    <g:if test="${more}">
        <script type="text/javascript">$('twitter_links').hide();</script>
        <g:each var="item" in="${more}">
            <p style="padding-left: 20px">• <twitterChecker:parseLinks>${item.text}</twitterChecker:parseLinks> <span class="date">(<g:formatDate format="dd MMM yyyy" date="${item.createdAt}"/>)</span></p>
        </g:each>
        <p style="padding-left: 20px">• <a href="#" onclick="Effect.BlindUp('twitter_more'); $('twitter_links').appear({ duration: 1.5 }); return false;">display less twitter messages</a>&nbsp;&nbsp;&nbsp;• <a href="http://twitter.com/6footplus">6footplus on twitter.com</a></p>
    </g:if>

