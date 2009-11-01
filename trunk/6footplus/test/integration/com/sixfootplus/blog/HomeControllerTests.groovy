package com.sixfootplus.blog

import grails.test.*

class HomeControllerTests extends GrailsUnitTestCase {

    HomeController hc

    protected void setUp() {
        super.setUp()

        hc = new HomeController()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testIndex() {

        def model = hc.index()

        def articles = model.articles
        def count = model.blogCount

        assertEquals("Expected count to match!", 15, count)
        assertEquals("Expected single article!", 3, articles.size())
    }

    void testRecent() {

        def model = hc.recent()

        def articles = model.articles

        assertEquals("Expected count to match :", 5, articles.size())
        assertEquals("Expected article id 5 :", 5, articles[0].id)
    }

    void testShow() {

        hc.show()

        assertEquals("Expected flash message :", "Article not found with id null", hc.flash.message)

        hc.params.id = 900
        hc.show()

        assertEquals("Expected flash message :", "Article not found with id 900", hc.flash.message)

        hc.params.id = 2
        def model = hc.show()

        assertEquals("Expected article with id 2 :", 2, model.article.id)
    }

}