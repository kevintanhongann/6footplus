<html>
    <head>
        <meta name="layout" content="simple"/>
        <title>6footplus.com - contact</title>
        <p:css name='contact'/>
    </head>
    <body id="contact-page">
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>
        <span class="heading">contact</span>
        <p><img style="border-style: solid; border-color: rgb(102, 102, 51);  margin-top: 0px; margin-left: 10px; float:right" border="1" src="http://www.gravatar.com/avatar/a255cd6bb89a855fa55baed5817fcce8.png"/>you can get a hold of me on my mobile phone +49 176 4815 7373, <g:link controller="sms">send me</g:link> an sms, email me at me&#64;6footplus.com or simply submit the simple form below.</p>
        <p>my CV (curriculum vitae) is available as a <a href="/resources">download</a> in both Microsoft Word and PDF versions.</p>
        <div style="margin-top: 15px; margin-bottom: 0px"><a href="http://friendfeed.com/6footplus"><tooltip:tip value="my friendfeed.com"><img src="/images/friendfeed_32.png" width="22" border="0"/></tooltip:tip></a>&nbsp;&nbsp;&nbsp;<a href="/feed/rss"><tooltip:tip value="my blog rss feed"><img src="/images/feed_32.png" width="22" border="0"/></tooltip:tip></a>&nbsp;&nbsp;&nbsp;<a href="http://www.linkedin.com/in/andreasnerlich"><tooltip:tip value="my profile on linkedin.com"><img src="/images/linkedin_32.png" width="22" border="0"/></tooltip:tip></a>&nbsp;&nbsp;&nbsp;<a href="http://twitter.com/6footplus"><tooltip:tip value="my twitter.com messages"><img src="/images/twitter_32.png" border="0" width="22"/></tooltip:tip></a>&nbsp;&nbsp;&nbsp;<a href="http://vimeo.com/sixfootplus/videos"><tooltip:tip value="my videos on vimeo.com"><img src="/images/vimeo_32.png" width="22" border="0"/></tooltip:tip></a>&nbsp;&nbsp;&nbsp;<a href="http://www.flickr.com/photos/6footplus/"><tooltip:tip value="my photos on flickr.com"><img src="/images/flickr_32.png" border="0" width="22"/></tooltip:tip></a></div>
        <div id="contactFormWrapper">
            <g:include controller="contact" action="showForm" />
        </div>
        <p style="margin-bottom: 100px"/>
    </body>
</html>