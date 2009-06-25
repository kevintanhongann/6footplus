class BlogCommentTest extends AbstractWebTesting {
    
    def test01Login() {

        group(description:'Invoke authentication') {
            invoke      'blogComment'
            verifyText  'Username'
            verifyText  'Password'
        }

        loginSubmit();
    }

    def test02IndexEditAndDelete(){

        test01Login()

        group(description:'List of comments') {
            verifyText  'Sort by'
            clickLink   'Next'
            clickLink   'Previous'
        }

        group(description:'Edit comment') {
            clickLink(label: 'edit', href: '/blogComment/edit/105');
            verifyText  'Author'
            verifyText  'IP'
            verifyText  'Message'
            verifyText  'Article'
            verifyText  'Created'
            clickButton 'Update'
            verifyText  'Comment 105 updated'
        }

        group(description:'Delete comment') {
            clickLink(label: 'delete', href: '/blogComment/delete/106');
            verifyText  'BlogComment 106 deleted'
        }
    }
}