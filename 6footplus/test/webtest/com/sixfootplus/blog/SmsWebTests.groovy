package com.sixfootplus.blog

class SmsWebTests extends AbstractWebTesting {
    
    void test01Login() {

        group(description:'Invoke authentication') {
            invoke      '/sms/pimped'
            verifyText  'Username'
            verifyText  'Password'
        }

        loginSubmit();
    }

    void test02InvalidSubmit() {

        config(easyajax:true)

        group(description:'Invoke SMS page') {
            invoke      '/sms'
            setInputField(name:"message", value:"a sms message")
            setInputField(name:"captcha", value:"")
            clickButton(label:"Send")
            //verifyXPath xpath:  "/html/body/div[@id='outer-container']/div[@id='content-container']/div[@id='smsFormWrapper']/div[@class='errorsms']",
            verifyXPath xpath:  "//*[@class='errorsms']"
            text:   'Incorrect Maths addition answer'
        }
    }

    void test03Pimped() {

        test01Login()

        group(description:'Invoke pimped SMS page') {
            verifyText  'Stored:'
            verifyText  'Recipient:'
        }
    }

}