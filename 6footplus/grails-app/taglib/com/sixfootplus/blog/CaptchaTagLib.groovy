package com.sixfootplus.blog

class CaptchaTagLib {

    static namespace = "cm"

    def captchaRequest = { attrs ->
        def words = ['zero','one','two','three','four','five','six','seven','eight','nine','ten']
        def a = new Random().nextInt(10)
        def b = new Random().nextInt(10)
        session.captcha = (a + b).toString()
        out << "What is ${words[a]} + ${words[b]}?"
    }
}
