<div style="height: 40px">
    <div id="contact_spinner" style="display: none;"><p class="date"><img style="vertical-align:middle" src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Sending..."/> Sending...</p></div>
</div>

<g:formRemote name="contactForm" url="[controller:'contact', action:'send']"
              update="contactFormWrapper"
              onLoading="disableForm(); showSpinner('contact_spinner')" onLoaded="hideSpinner('contact_spinner')">
    <form:textField name="name" label="Name" bean="${contactForm}" />
    <form:textField name="email" label="Email" bean="${contactForm}" />
    <form:textField name="subject" label="Subject" bean="${contactForm}" />
    <form:textarea name="message" label="Message" bean="${contactForm}" />

    <form:fieldWrapper label="&nbsp;" name="captcha" bean="${contactForm}" >
        <div id="captchaChallenge"><cm:captchaRequest /></div>
        <div id=captchaResponse">
            <input id="captchaResponse" type="text" name="captcha" />
        </div>
    </form:fieldWrapper>

    <input type="submit" value="Send" class="button"/>
</g:formRemote>

<g:javascript>
    function disableForm() {
        $('contactForm').disable();
    }
</g:javascript>
