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
        <p>you can get hold of me on my mobile phone +49 176 4815 7373 or email at me&#64;6footplus.com or simply send me a message by submitting the fancy ajax form below. if you're interested, my curriculum vitae is available as a <a href="/resources">download</a> in both Microsoft Word and PDF versions.</p>
        <div id="contactFormWrapper">
            <g:include controller="contact" action="showForm" />
        </div>
    </body>
</html>