package com.sixfootplus.blog

class UploadWebTests extends AbstractWebTesting {
    
    void test01Login() {

        group(description:'Invoke authentication') {
            invoke      'upload'
            verifyText  'Username'
            verifyText  'Password'
        }

        loginSubmit();
    }
}