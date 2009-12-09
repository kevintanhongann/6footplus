<g:javascript>
    var maxMessages = 1;
    var maxChars = 140;

    function limitText(limitField) {
        if (limitField.value.length > maxChars) {
            limitField.value = limitField.value.substring(0, maxChars);
        }
        showLeft(limitField);
    }

    function showLeft(limitField) {
        var str = new String(limitField.value);
        var curlength = str.length;
        var nrMess = Math.floor((curlength-1) / (maxChars / maxMessages)) + 1;
        var elt = document.getElementById('messagespaged');
        elt.innerHTML = "(" + curlength + " / " + nrMess + ")";
    }

    function updateRecipient() {
        var index = document.forms[0].stored.selectedIndex;
        document.forms[0].recipient.value = document.forms[0].stored[index].value;
    }
</g:javascript>

<g:if test="${smsForm.hasRecipient}">
    <p>select one of the commonly used recipients or enter a new number into the recipient input field:</p>
</g:if>
<g:else>
    <p>by submitting a message below, an SMS will be delivered to <g:link controller="contact" action="index">my mobile phone</g:link>:</p>
</g:else>

<g:eachError bean="${smsForm}"><div class="errorsms"><g:message error="${it}"/></div></g:eachError><br/>

<g:formRemote name="smsForm" url="[controller:'sms', action:'send']"
              update="smsFormWrapper"
              onLoading="disableForm(); showSpinner('sms_spinner'); showSpinner('sms_patience')" onLoaded="hideSpinner('sms_spinner'); hideSpinner('sms_patience')">
    <g:if test="${smsForm.hasRecipient}">
        <g:hiddenField name="hasRecipient" value="${smsForm.hasRecipient}" />
        <label>Stored:</label>
        <select name="stored" onchange="updateRecipient(this)"/>
            <option value="+27828281416">+27828281416 (Roland)</option>
            <option value="+436503471632">+436503471632 (Pops)</option>
            <option value="+436645729440">+436645729440 (Moms)</option>
            <option value="+436645921962">+436645921962 (Oma)</option>
        </select><br/><br/>
        <label>Recipient:</label>
        <g:textField name="recipient" style="width:150px" value="${smsForm.recipient}" /><br/>
    </g:if>
    <label>Message: <br/><br/><span id="messagespaged" style="color: #BBBBBB;">(0 / 0)</span></label>
    <g:textArea id="message_sms" name="message" onkeyup="limitText(this);" onkeydown="limitText(this);" value="${smsForm?.message}" />
    <label>&nbsp;</label>
    <div id="captchaChallenge"><cm:captchaRequest /></div>
    <div id=captchaResponse">
        <input id="captchaResponse" type="text" name="captcha" />
    </div>
    <div style="height: 30px;width:80px;float:left">
        <div id="sms_spinner" style="display: none;"><span class="date"><img style="vertical-align:middle" src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Sending"/> Sending</span></div>
    </div>
    <input type="submit" value="Send" class="buttonsms"/>
    <div id="sms_patience" style="display:none;color:#BBBBBB;">
        <p>Dispatching the SMS may take a few seconds...</p>
    </div>
</g:formRemote>

<g:javascript>
    function disableForm() {
        $('smsForm').disable();
    }
</g:javascript>
