package com.sixfootplus.blog

class TagWebTests extends AbstractWebTesting {

    void test01Login() {

        group(description:'Confirm no auth required and showing of tags') {
            invoke(url: '/tag/show')
            verifyText(text: 'techie')
            verifyText(text: '16')
        }
    }
}