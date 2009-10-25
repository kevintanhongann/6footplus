package com.sixfootplus.blog

import grails.test.*

class TwitterStatusTests extends GrailsUnitTestCase {

    def twitter

    protected void setUp() {

        super.setUp()

        // Make sure we can invoke validate() on our domain object.
        mockForConstraintsTests(TwitterStatus)

        twitter = new TwitterStatus()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraintsNullable() {

        assertFalse twitter.validate()

        assertEquals 'nullable', twitter.errors['text']
        assertEquals null, twitter.errors['id'] //due to id being of type long
        
        assertEquals 2, twitter.errors.allErrors.size()
    }
}
