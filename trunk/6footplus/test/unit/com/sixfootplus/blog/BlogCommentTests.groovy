package com.sixfootplus.blog

import grails.test.*

class BlogCommentTests extends GrailsUnitTestCase {

    def comment

    protected void setUp() {

        super.setUp()

        // Make sure we can invoke validate() on our domain object.
        mockForConstraintsTests(BlogComment)

        // Set up default comment, so we can easily test single properties.
        comment = new BlogComment()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraintsNullable() {

        assertFalse comment.validate()

        assertEquals 'nullable', comment.errors['author']
        assertEquals 'nullable', comment.errors['message']
        assertEquals 'nullable', comment.errors['article']

        assertEquals 3, comment.errors.allErrors.size()
    }

    void testConstraintsSize() {

        def author = new StringBuffer()
        def message = new StringBuffer("a")
        def article = new BlogArticle()

        //exceed the limit of 40 chars
        (1..41).each {
            author << "a"
        }

        comment.setAuthor(author.toString())
        comment.setMessage(message.toString())
        comment.setArticle(article)

        assertFalse comment.validate()

        assertEquals 'size', comment.errors['author']
        assertEquals 'size', comment.errors['message']

        assertEquals 2, comment.errors.allErrors.size()

        //valid values
        comment.setMessage("aaaaa")
        comment.setAuthor("a")

        assertTrue comment.validate()

        //allow html
        comment.setMessage("aaa <p>")

        //epect validate to fail
        assertTrue comment.validate()

        //exceed the limit of 1000 chars
        (1..1001).each {
            message << "a"
        }

        comment.setMessage(message.toString())

        //epect validate to fail
        assertFalse comment.validate()

        assertEquals 'size', comment.errors['message']
    }
}
