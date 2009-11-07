class TagTest extends AbstractWebTesting {

    def test01Login() {

        group(description:'Confirm no auth required and showing of tags') {
            invoke      '/tag/show'
            verifyText  'techie'
            verifyText  '16'
        }
    }
}