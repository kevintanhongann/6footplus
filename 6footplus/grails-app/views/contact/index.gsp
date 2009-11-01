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
        <p>you can get hold of me on my mobile phone +49 176 4815 7373 (send me an sms <g:link controller="sms">here</g:link>) or email me at me&#64;6footplus.com or simply submit the fancy ajax form below.</p>
        <p>if you're interested, my curriculum vitae is available as a <a href="/resources">download</a> in both Microsoft Word and PDF versions.</p>
        <div id="contactFormWrapper">
            <g:include controller="contact" action="showForm" />
        </div>
        <p/>
    </body>
</html>