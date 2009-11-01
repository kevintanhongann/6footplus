<html>
    <head>
        <meta name="layout" content="simple"/>
        <title>6footplus.com - sms</title>
        <p:css name='contact'/>
    </head>
    <body id="contact-page">
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>
        <span class="heading">sms</span>
        <p>send me an SMS to my mobile phone +49 176 4815 7373</p>
        <div id="smsFormWrapper" style="width:600px">
            <g:include controller="sms" action="showForm" />
        </div>
    </body>
</html>