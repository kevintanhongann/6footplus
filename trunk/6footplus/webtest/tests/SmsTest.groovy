class SmsTest extends AbstractWebTesting {
    
    def test01Login() {
        invoke      '/sms'
        verifyText  'send me an SMS to my mobile phone +49 176 4815 7373'
    }

    def test02InvalidSubmit() {

        group(description:'Invoke SMS page') {
            invoke      '/sms'
            setInputField(name:"message", value:"a sms message")
            setInputField(name:"captcha", value:"")
            clickButton(label:"Send")
            verifyXPath xpath:  "/html/body/div[@id='outer-container']/div[@id='content-container']/div[@id='smsFormWrapper']/div[@class='errorsms']",
                        text:   'Incorrect Maths addition answer'
        }
    }
}