package com.sixfootplus.blog

import grails.test.*

class BlogLinkTests extends GrailsUnitTestCase {

    def link

    protected void setUp() {

        super.setUp()

        // Make sure we can invoke validate() on our domain object.
        mockForConstraintsTests(BlogLink)

        // Set up default link, so we can easily test single properties.
        link = new BlogLink()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraintsNullable() {

        assertFalse link.validate()

        assertEquals 'nullable', link.errors['label']
        assertEquals 'nullable', link.errors['url']
        assertEquals 'nullable', link.errors['article']

        assertEquals 3, link.errors.allErrors.size()
    }

    void testConstraintsSize() {

        def label = new StringBuffer()
        def url = new StringBuffer()
        def article = new BlogArticle()

        //exceed the limit of 128 chars
        (1..129).each {
            label << "a"
        }

        //exceed the limit of 128 chars
        (1..1001).each {
            url << "a"
        }

        link.setLabel(label.toString())
        link.setUrl(url.toString())
        link.setArticle(article)

        assertFalse link.validate()

        assertEquals 'size', link.errors['label']
        assertEquals 'size', link.errors['url']

        assertEquals 2, link.errors.allErrors.size()

        //valid values
        link.setLabel("a")
        link.setUrl("a")

        assertTrue link.validate()
    }
}
