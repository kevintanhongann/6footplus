package com.sixfootplus.blog.controller

import grails.test.*

class SmsControllerTests extends GrailsUnitTestCase {
    
    SmsController sc

    protected void setUp() {
        super.setUp()

        sc = new SmsController()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testIndex() {

        sc.index()
    }

    void testSend() {

        sc.send()

        assert sc.response.contentAsString
    }
}
