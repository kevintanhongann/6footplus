package com.sixfootplus.blog.controller

class ContactControllerTests extends grails.test.ControllerUnitTestCase {

    void testIndex() {

        def controller = newInstance()

        controller.index()

        assertNull controller.modelAndView.model
        assertNull controller.modelAndView.view
    }

    void testshowForm() {

        def controller = newInstance()

        controller.showForm()

        assertEquals "contactForm", renderArgs.template
        assertNotNull renderArgs.model.contactForm
    }
}