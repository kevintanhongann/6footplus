package com.sixfootplus.blog.controller

class ContactControllerTests extends grails.test.ControllerUnitTestCase {

    void testIndex() {

        def controller = newInstance()

        controller.index()

        assert controller.modelAndView.model.size() == 0
        assertNull controller.modelAndView.view
    }

    void testshowForm() {

        def controller = newInstance()

        controller.showForm()

        assertEquals "contactForm", renderArgs.template
        assertNotNull renderArgs.model.contactForm
    }
}