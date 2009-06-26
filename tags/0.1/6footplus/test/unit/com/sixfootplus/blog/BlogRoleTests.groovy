package com.sixfootplus.blog

import grails.test.*

class BlogRoleTests extends GrailsUnitTestCase {

    def role

    protected void setUp() {

        super.setUp()

        // Make sure we can invoke validate() on our domain object.
        mockForConstraintsTests(BlogRole)

        // Set up default link, so we can easily test single properties.
        role = new BlogRole()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraintsNullable() {

        assertFalse role.validate()

        assertEquals 'nullable', role.errors['name']
        assertEquals 1, role.errors.allErrors.size()
    }

    void testConstraintsBlank() {

        role = new BlogRole(name: '')

        assertFalse role.validate()

        assertEquals 'blank', role.errors['name']
        assertEquals 1, role.errors.allErrors.size()
    }

    void testConstraintsUnique() {

        role = new BlogRole(name: 'ADMIN')

        def duplicate = new BlogRole(name: 'ADMIN')
        mockForConstraintsTests(BlogRole, [duplicate])

        assertFalse role.validate()
        assertEquals 'Name is not unique.', 'unique', role.errors['name']
    }
}
