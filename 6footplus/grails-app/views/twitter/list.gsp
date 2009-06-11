<g:each var="item" in="${result}">
    <p style="padding-left: 20px">• <twitter:markup text='${item.text}'/> <span class="date">(posted <script type="text/javascript">document.write(prettyDate("<g:formatDate format="yyyy-MM-dd'T'HH:mm:ss'Z'" date="${item.createdAt}"/>"));</script>)</span></p>
</g:each>
<div id="twitter_spinner" style="display: none;"><p class="date"><img style="vertical-align:middle" src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Loading..."/> Loading...</p></div>
<div id="twitter_more"></div>
<div id="twitter_links"><p style="padding-left: 20px">• <g:remoteLink controller="twitter" onLoading="${'twitter_links'}.hide(); showSpinner('twitter_spinner')" onLoaded="hideSpinner('twitter_spinner')" onComplete="Effect.SlideDown('twitter_more'); return false;" action="more" update="twitter_more" class="light">display more twitter messages</g:remoteLink>&nbsp;&nbsp;&nbsp;• <twitter:userLink name="6footplus" text="follow me on twitter"/></p></div>
