package com.sixfootplus.blog

class SmsController {

    def index = { }

    def showForm = {
        render(template: 'smsForm', model:[smsForm: new SmsForm()])
    }

    def send = { SmsForm smsForm ->

        def captchaValid = smsForm.captcha == session.captcha
        if(!captchaValid) {
            smsForm.errors.rejectValue('captcha', null, "Incorrect Maths addition answer")
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

                //def urlString = "http://6footplus.com/resources/response.xml"
                def urlString = "https://www.voipdiscount.com/myaccount/sendsms.php?username=" + username + "&password=" + password + "&to=+4917648157373&text=" + smsForm.message.encodeAsURL()
                def url = new URL(urlString)

                URLConnection conn = url.openConnection()

                if (conn.responseCode != 200) {
                    providerResponse = new SmsResponse(result:"${conn.responseCode} ${conn.responseMessage}")
                } else {
                    String text = new String(conn.content.text).trim()
                    try {
                        def responseMessage = new XmlParser().parseText(text)
                        providerResponse = new SmsResponse(result:responseMessage.resultstring.text(), description:responseMessage.description.text(), cause:responseMessage.endcause.text())
                    } catch (Exception e){
                        println "unable to parse sms provider response : " + text
                        providerResponse = new SmsResponse(result:"failure", cause:"unable to parse response from sms provider")
                    }
                }

            } else {
                providerResponse = new SmsResponse(cause:"SMS currently not enabled/configured")
            }

            try {
                //email me that someone tried to sms
                providerResponse.ip = request.getRemoteAddr();
                providerResponse.message = smsForm.message
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

    static constraints = {
        message(nullable: false, blank: false, size: 5..160)
    }
}

class SmsResponse {
    
    String result
    String description
    String cause
    String ip
    String message
}


