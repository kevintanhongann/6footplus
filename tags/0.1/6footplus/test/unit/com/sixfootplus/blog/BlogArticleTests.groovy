package com.sixfootplus.blog

import grails.test.*

class BlogArticleTests extends GrailsUnitTestCase {
    
    def article

    protected void setUp() {

        super.setUp()

        // Make sure we can invoke validate() on our domain object.
        mockForConstraintsTests(BlogArticle)

        // Set up default article, so we can easily test single properties.
        article = new BlogArticle(subject: 'subject', body: 'body body')
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraintsNullable() {

        assertEquals "subject", article.subject
        assertEquals "body body", article.body

        article.setStatus(null)

        assertFalse article.validate()

        assertEquals 'nullable', article.errors['author']
        assertEquals 'nullable', article.errors['status']

        assertEquals 2, article.errors.allErrors.size()

        //correct previously incorrect property
        article.setAuthor(new BlogUser())
        article.setStatus(ArticleStatus.PUBLISHED)

        assertTrue article.validate()
    }

    void testConstraintsSizeExceeded() {

        def subject = new StringBuffer()
        def body = new StringBuffer()

        (1..201).each {
            subject << "a"
        }

        (1..10001).each {
            body << "a"
        }

        assertEquals 201, subject.size()
        assertEquals 10001, body.size()

        article.setSubject(subject.toString())
        article.setBody(body.toString())

        assertFalse "Validation succeeded", article.validate()
        assertEquals 'Subject size constraint failed!', 'size', article.errors['subject']
        assertEquals 'Body size constraint failed!', 'size', article.errors['body']
    }

    void testConstraintsSize() {

        def subject = "aaaa"
        def body = "aaaa"

        assertEquals 4, subject.size()
        assertEquals 4, body.size()

        article.setSubject(subject)
        article.setBody(body)

        assertFalse "Validation succeeded", article.validate()
        assertEquals 'Subject size constraint failed!', 'size', article.errors['subject']
        assertEquals 'Body size constraint failed!', 'size', article.errors['body']

        subject += "a"
        body += "a"

        article.setSubject(subject)
        article.setBody(body)
        article.setAuthor(new BlogUser())

        assertTrue article.validate()
    }
}
