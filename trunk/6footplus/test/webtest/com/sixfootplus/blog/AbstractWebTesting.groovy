package com.sixfootplus.blog

abstract class AbstractWebTesting extends grails.util.WebTest {

    abstract void test01Login()

    void loginSubmit() {

        group(description: 'Submit login'){
            setInputField(name:"username", value:"admin")
            setInputField(name:"password", value:"geheim")
            clickButton(label:"login")
            verifyText 'Logged in as: admin'
        }
    }
}

