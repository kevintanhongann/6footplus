class BlogArticleTest extends AbstractWebTesting {
    
    def test01Login() {

        group(description:'Invoke authentication') {
            invoke      'blogArticle'
            verifyText  'Username'
            verifyText  'Password'
        }

        loginSubmit();
    }

    def test02IndexShowUpdateAndDelete() {

        test01Login()

        group(description: 'Paginated list of articles'){
            verifyText  'Beitrag Nr.1'
            verifyText  'Beitrag Nr.9'
            verifyText  'Next'
            clickLink   'Next'
            verifyText  'Beitrag Nr.10'
            verifyText  'Beitrag Nr.15'
            clickLink   'Previous'
            verifyText  'Beitrag Nr.1'
        }

        group(description: 'Show an article') {
            clickLink   'Beitrag Nr.1'
            verifyText  'Beitrag Nr.1'
            verifyText  'Dies ist ein Testeintrag mit der Nr.1'
        }

        group(description: 'Edit an article') {
            invoke      'blogArticle'
            clickLink   'Beitrag Nr.2'
            clickLink   'Beitrag Nr.2'
            verifyText  'Subject:'
            verifyText  'Body:'
            verifyText  'Status:'
            verifyText  'Created:'
            verifyText  'Updated:'
            clickButton(label:"Update")
            verifyText  'Article 3 updated.'
        }

        group(description: 'Delete an article') {
            clickLink   'delete'
            verifyText  'Article 3 deleted.'
        }
    }
}