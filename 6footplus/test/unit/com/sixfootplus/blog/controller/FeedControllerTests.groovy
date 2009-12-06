package com.sixfootplus.blog.controller

class FeedControllerTests extends grails.test.ControllerUnitTestCase {

    void testRss() {

        def controller = newInstance()

        //no idea how to mock render
        controller.rss()
    }
}