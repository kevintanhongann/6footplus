class UploadTest extends AbstractWebTesting {
    
    def test01Login() {

        group(description:'Invoke authentication') {
            invoke      'upload'
            verifyText  'Username'
            verifyText  'Password'
        }

        loginSubmit();
    }
}