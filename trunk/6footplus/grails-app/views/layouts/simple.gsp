<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="6footplus.com"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <link rel="SHORTCUT ICON" href="${createLinkTo(dir: 'images', file: 'favicon.ico')}"/>
    <p:css name='lightbox'/>
    <p:css name='6'/>
    <g:layoutHead/>
    <p:javascript src="application" />
    <p:javascript src="prototype/prototype" />
    <p:javascript src="prototype/effects" />
    <p:javascript src="prototype/scriptaculous"/>
    <p:javascript src="rotateImg" />
    <p:javascript src="lightbox" />
    <p:javascript src="spinner" />
    <script type="text/javascript">var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www."); document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));</script>
    <script type="text/javascript">var pageTracker = _gat._getTracker("UA-590454-1"); pageTracker._trackPageview();</script>
</head>
<body>
<div id="outer-container">
    <g:render template="/common/header"/>
    <div id="content-container">
        <g:layoutBody/>
    </div>
</div>
</body>
</html>