package com.sixfootplus.blog

import grails.test.*

class RssFeedTests extends GrailsUnitTestCase {

    def feed

    protected void setUp() {

        super.setUp()

        // Make sure we can invoke validate() on our domain object.
        mockForConstraintsTests(RssFeed)

        feed = new RssFeed()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraintsNullable() {

        assertFalse feed.validate()

        assertEquals 'nullable', feed.errors['producer']
        assertEquals 'nullable', feed.errors['title']
        assertEquals 'nullable', feed.errors['description']
        assertEquals 'nullable', feed.errors['link']
        assertEquals 'nullable', feed.errors['thumbnail']
        assertEquals 'nullable', feed.errors['pubDate']

        assertEquals 6, feed.errors.allErrors.size()
    }

    void testToString() {

        def date = new Date()

        feed = new RssFeed(producer: "a", title: "b", description: "c", link: "d", thumbnail: "e", pubDate: date)

        assertEquals "a, b, c, d, e, " + date, feed.toString()
    }
}
