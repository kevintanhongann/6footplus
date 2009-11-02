class SmsTest extends AbstractWebTesting {
    
    def test01Login() {

        group(description:'Invoke authentication') {
            invoke      '/sms/pimped'
            verifyText  'Username'
            verifyText  'Password'
        }

        loginSubmit();
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

    def test03Pimped() {

        test01Login()

        group(description:'Invoke pimped SMS page') {
            verifyText  'Stored:'
            verifyText  'Recipient:'
        }
    }

}