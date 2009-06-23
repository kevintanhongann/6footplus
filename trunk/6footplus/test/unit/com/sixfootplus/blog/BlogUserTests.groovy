package com.sixfootplus.blog

import grails.test.*
import org.jsecurity.crypto.hash.Sha1Hash

class BlogUserTests extends GrailsUnitTestCase {
    
    def user

    protected void setUp() {

        super.setUp()

        // Make sure we can invoke validate() on our domain object.
        mockForConstraintsTests(BlogUser)

        // Set up default link, so we can easily test single properties.
        user = new BlogUser()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraintsNullable() {

        assertFalse user.validate()

        assertEquals 'nullable', user.errors['username']
        assertEquals 'nullable', user.errors['password']
        assertEquals 'nullable', user.errors['email']

        assertEquals 3, user.errors.allErrors.size()
    }

    void testConstraintsBlank() {

        user = new BlogUser(username: '', password: '', email: '')

        assertFalse user.validate()

        assertEquals 'blank', user.errors['username']
        assertEquals 'blank', user.errors['password']

        assertEquals 2, user.errors.allErrors.size()
    }

    void testConstraintsUnique() {

        user = new BlogUser(username: 'username', password: 'password', email: 'user@something.com')

        def duplicate = new BlogUser(username: 'username', password: 'password', email: 'user@something.com')
        mockForConstraintsTests(BlogUser, [duplicate])

        assertFalse user.validate()
        assertEquals 'Username is not unique.', 'unique', user.errors['username']
    }

    void testConstraintsMatches() {

        user = new BlogUser(username: 'username!', password: 'password', email: 'user@something.com')

        assertFalse user.validate()
        assertEquals 'Username does not match pattern', 'matches', user.errors['username']
    }

    void testConstraintsSizeMinimum() {

        user = new BlogUser(username: 'us', password: 'password', email: 'user@something.com')

        assertFalse user.validate()
        assertEquals 'Username size', 'size', user.errors['username']
        assertEquals 40, user.getPassword().size()
    }

    void testConstraintsSizeMximum() {

        user = new BlogUser(password: 'password', email: 'user@something.com')
        def username = new StringBuffer()

        //exceed the limit of 128 chars
        (1..31).each {
            username << "a"
        }

        user.setUsername(username.toString())

        assertFalse user.validate()
        assertEquals 'Username size', 'size', user.errors['username']
    }

    void testConstraintsPassword() {

        user = new BlogUser(username: 'username', password: 'password', email: 'user@something.com')

        assertTrue user.validate()

        assertEquals new Sha1Hash('password').toHex(), user.getPassword()
    }

    void testConstraintsEmail() {

        user = new BlogUser(username: 'username', password: 'password', email: 'something.com')

        assertFalse user.validate()

        assertEquals 'Email pattern', 'email', user.errors['email']
    }
}
