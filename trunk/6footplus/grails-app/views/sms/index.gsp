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
        <div id="smsFormWrapper" style="width:600px">
            <g:include controller="sms" action="showForm" />
        </div>
    </body>
</html>