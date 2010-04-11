package com.sixfootplus.blog

class BlogLinkWebTests extends AbstractWebTesting {
    
    void test01Login() {

        group(description:'Invoke authentication') {
            invoke      'blogLink/list'
            verifyText  'Username'
            verifyText  'Password'
        }

        loginSubmit();
    }

    void test02CreateEditShowAndDelete(){

        test01Login()

        group(description:'Create a link') {
            clickLink   'create a link'
            verifyText  'Label'
            verifyText  'URL'
            verifyText  'Article'
            setInputField(name: 'label', value: 'test label')
            setInputField(name: 'url', value: 'test url')
            setSelectField(name: 'article.id', text: 'Beitrag Nr.3')
            clickButton 'Save'
            verifyText  'Link 1 created.'
            verifyText  'test label'
            verifyText  'test url'
            verifyText  'Beitrag Nr.3'
        }

        group(description:'Edit a link') {
            clickLink   'edit'
            verifyText  'Label'
            verifyText  'URL'
            verifyText  'Article'
            setInputField(name: 'label', value: 'test label updated')
            setInputField(name: 'url', value: 'http://test_updated')
            setSelectField(name: 'article.id', text: 'Beitrag Nr.1')
            clickButton 'Update'
            verifyText  'Link 1 updated'
            verifyText  'test label updated'
            verifyText  'http://test_updated'
            verifyText  'Beitrag Nr.1'
        }

        group(description:'Show links') {
            invoke      'blogLink'
            verifyText  'test label updated'
            verifyText  'Beitrag Nr.1'
            verifyText  'http://test_updated'
        }


        group(description:'Delete a link') {
            clickLink(label: 'delete', href: '/blogLink/delete/1');
            verifyText  'BlogLink 1 deleted'
        }
    }
}