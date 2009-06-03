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
    <p:javascript src="lightbox" />
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