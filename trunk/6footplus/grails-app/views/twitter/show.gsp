<g:if test="${status != null}">
    <div style="padding: 20px">
        <div style="float:left; width:20px; padding-right: 10px; padding-left: 20px"><img src="/images/quote_left.png"/></div>
        <div style="float:left;width:80%; font-style:italic; font-size: 13px; font-family:Georgia"><twitterChecker:parseLinks>${status.text}</twitterChecker:parseLinks> <span class="date">(posted <script type="text/javascript">document.write(prettyDate("<g:formatDate format="yyyy-MM-dd'T'HH:mm:ss'Z'" date="${status.createdAt}"/>"));</script>)</span></div>
        <div style="clear:both"></div>
    </div>    
</g:if>
