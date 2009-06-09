<html>
<head>
    <title><g:layoutTitle default="6footplus.com"/></title>
    <feed:meta kind="atom" version="1.0" controller="feed" action="atom"/>
    <feed:meta kind="rss" version="2.0" controller="feed" action="rss"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <link rel="SHORTCUT ICON" href="${createLinkTo(dir: 'images', file: 'favicon.ico')}"/>
    <p:css name='lightbox'/>
    <p:css name='6'/>
    <g:layoutHead/>
    <p:javascript src="application" />
    <p:javascript src="prototype/prototype" />
    <p:javascript src="prototype/effects" />
    <p:javascript src="prototype/scriptaculous"/>
    <p:javascript src="rotateImg" />
    <p:javascript src="spinner" />
    <p:javascript src="lightbox" />
    <script type="text/javascript">var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www."); document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));</script>
    <script type="text/javascript">var pageTracker = _gat._getTracker("UA-590454-1"); pageTracker._trackPageview();</script>
</head>
<body>
<div id="outer-container">
    <g:render template="/common/header"/>
    <div id="content-container">
        <g:layoutBody/>
    </div>
    <g:render template="/common/footer"/>
</div>
</body>
</html>