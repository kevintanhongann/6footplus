class ContactTest extends AbstractWebTesting {

    def test01Login() {
        invoke      '/contact'
        verifyText  'contact'
    }

    def test02Submit() {

        group(description:'Verify contact form'){
            invoke      '/contact'
            verifyText  'contact'
            verifyText  'Name'
            verifyText  'Email'
            verifyText  'Subject'
            verifyText  'Message'
            verifyText  'What is'
        }

        group(description:'Submit form'){
            invoke      '/contact'
            setInputField(name: 'name', value: 'john')
            setInputField(name: 'email', value: 'john@doe.com')
            setInputField(name: 'subject', value: 'What is up?')
            setInputField(name: 'message', value: 'Fancy going for a beer?')
            setInputField(name: 'captcha', value: 'bot')
            clickButton "Send" //this will fail due to incorrect captcha value

            //TODO figure out how to use 'not'
            verifyText  'Name'
            verifyText  'Email'
        }
    }
}