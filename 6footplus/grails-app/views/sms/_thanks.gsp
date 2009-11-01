<div class="message">
    <g:if test="${providerResponse?.result == 'success'}">
        Thanks for sending me an SMS!
    </g:if>
    <g:else>
        Did not get a successful response. See receipt for more details!
    </g:else>
</div>
<p class="heading">receipt</p>
<g:if test="${providerResponse?.result}">
     result : ${providerResponse.result}<br/>
</g:if>
<g:if test="${providerResponse?.description}">
     description : ${providerResponse.description}<br/>
</g:if>
<g:if test="${providerResponse?.cause}">
     cause : ${providerResponse.cause}<br/>
</g:if>
<p><g:link controller="sms" action="index">send</g:link> me another sms</p>