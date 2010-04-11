package com.sixfootplus.blog

class BlogCommentWebTests extends AbstractWebTesting {
    
    void test01Login() {

        group(description:'Invoke authentication') {
            invoke      'blogComment/list'
            verifyText  'Username'
            verifyText  'Password'
        }

        loginSubmit();
    }

    void test02IndexEditAndDelete(){

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