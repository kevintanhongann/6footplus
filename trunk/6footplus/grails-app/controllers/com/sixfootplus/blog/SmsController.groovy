package com.sixfootplus.blog

import org.jsecurity.*

class SmsController {

    def index = {}

    def pimped = {

        render(view: "index")
    }

    def showForm = {

        if(SecurityUtils.getSubject() != null && SecurityUtils.getSubject().hasRole("ADMIN")){
            render(template: 'smsForm', model:[smsForm: new SmsForm(hasRecipient: true, recipient: "+4917648350800")])
        } else {
            render(template: 'smsForm', model:[smsForm: new SmsForm()])
        }
    }

    def send = { SmsForm smsForm ->

        def captchaValid = smsForm.captcha == session.captcha
        if(!captchaValid) {
            smsForm.errors.rejectValue('captcha', null, "Incorrect Maths addition answer")
        }

        // only admin users may alter the recipient
        if(smsForm.recipient) {
            def user = SecurityUtils.getSubject()
            if(user == null || !user.hasRole("ADMIN")) {
                smsForm.errors.rejectValue('recipient', null, "Only admin users may enter a recipient")
            }
        }

        if (captchaValid && !smsForm.hasErrors() ) {

            //response from sms provider
            def providerResponse

            //only ever expect one sms provider
            def config = SmsConfig.findAll()[0];

            if(config){

                //login credentials
                def username = new String(config.username.decodeBase64())
                def password = new String(config.password.decodeBase64())
                def mobile = new String(config.mobile.decodeBase64())
                
                //sender and recipient phone numbers
                def sender = mobile
                def recipient = mobile

                if(smsForm.recipient){
                    recipient = smsForm.recipient
                }

                //def urlString = "http://6footplus.com/resources/response.xml"
                def urlString = "https://www.voipdiscount.com/myaccount/sendsms.php?username=" + username + "&password=" + password + "&from=" + sender + "&to=" + recipient + "&text=" + smsForm.message.encodeAsURL()
                def url = new URL(urlString)

                URLConnection conn = url.openConnection()

                if (conn.responseCode != 200) {
                    providerResponse = new SmsResponse(result:"${conn.responseCode} ${conn.responseMessage}")
                } else {
                    String text = new String(conn.content.text).trim()
                    try {
                        def responseMessage = new XmlParser().parseText(text)
                        providerResponse = new SmsResponse(result:responseMessage.resultstring.text(), description:responseMessage.description.text(), cause:responseMessage.endcause.text(), message: smsForm.message.encodeAsHTML())
                    } catch (Exception e){
                        providerResponse = new SmsResponse(result:"failure", cause:"unable to parse response from sms provider", message: smsForm.message.encodeAsHTML())
                    }
                }

            } else {
                providerResponse = new SmsResponse(cause:"SMS currently not enabled/configured")
            }

            try {
                //email me that someone tried to sms
                providerResponse.ip = request.getRemoteAddr();
                providerResponse.message = smsForm.message.encodeAsHTML()
                sendMail {
                    to grailsApplication.config.contactme.to.address
                    subject "SMS submitted from 6footplus.com"
                    body( view: "emailBody", model:[providerResponse:providerResponse])
                }
            } catch (Exception e) {
                println e
            }
            render(template: 'thanks', model:[providerResponse:providerResponse])

            return
        }

        render(template: 'smsForm', model:[smsForm: smsForm])
    }
}

class SmsForm {

    String message
    String captcha
    String recipient
    boolean hasRecipient

    static constraints = {
        message(nullable: false, blank: false, size: 5..140)
        //, matches: /^[\w\d\s\.,;!?@%&*-:()<>]+$/, 
    }
}

class SmsResponse {
    
    String result
    String description
    String cause
    String ip
    String message
}


