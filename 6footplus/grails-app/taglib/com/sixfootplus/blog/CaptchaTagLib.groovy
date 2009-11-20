package com.sixfootplus.blog

import grails.util.*

class CaptchaTagLib {

    static namespace = "cm"

    def captchaRequest = { attrs ->

        def words = ['zero','one','two','three','four','five','six','seven','eight','nine','ten']
        def a = new Random().nextInt(10)
        def b = new Random().nextInt(10)

        //hack by setting captcha to specific value for testing purposes
        switch(GrailsUtil.environment) {
            case "test":
            session.captcha = "999"
            out << "What is 1 + 998?"
            break
            default:
            session.captcha = (a + b).toString()
            out << "What is ${words[a]} + ${words[b]}?"
        }
    }
}
