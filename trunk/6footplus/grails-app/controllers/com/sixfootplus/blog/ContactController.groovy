package com.sixfootplus.blog

class ContactController {

    def mailService

    def index = {
        //scaffolds to views/contact/index.gsp
    }

    def showForm = {
        render(template: 'contactForm', model:[contactForm: new ContactForm()])
    }

    def send = { ContactForm contactForm ->

        def captchaValid = contactForm.captcha == session.captcha
        if(!captchaValid) {
            contactForm.errors.rejectValue('captcha', null, "Invalid Captcha Response")
        }

        if (captchaValid && !contactForm.hasErrors() ) {

            try {
                sendMail {
                    to grailsApplication.config.contactme.to.address
                    from contactForm.email
                    subject "${(grailsApplication.config.contactme.subject.prefix ?: "Contact Me:")} ${contactForm.subject}"
                    body( view: grailsApplication.config.contactme.email.view ?: "emailBody", model:[contactForm:contactForm])
                }

            } catch (Exception ex){
                contactForm.errors.rejectValue('email', null, "Unable to send email for given address. Please verify!")
                render(template: 'contactForm', model:[contactForm: contactForm])
                return
            }

            render(template: 'thanks', model:[contactForm:contactForm])
            
            return
        }

        render(template: 'contactForm', model:[contactForm: contactForm])
    }
}

class ContactForm {

    String name
    String email
    String subject
    String message
    String captcha

    static constraints = {
        name(blank: false)
        email(blank: false, email: true)
        subject(blank: false)
        message(blank: false)
    }
}