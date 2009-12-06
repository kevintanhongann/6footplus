package com.sixfootplus.blog.controller

import com.sixfootplus.blog.*

class BlogCommentControllerTests extends grails.test.ControllerUnitTestCase {

    //    TODO findAll with params mock not working yet
    //    void testList() {
    //
    //        def controller = newInstance()
    //
    //        def comment = 
    //
    //        mockDomain(BlogComment, [comment])
    //
    //        def model = controller.list()
    //
    //        assertNotNull model
    //        assertNotNull model.comments
    //        assertEqauls 1, model.count
    //    }


    void testUpdate() {

        def controller = newInstance()

        mockDomain(BlogComment, [new BlogComment(
                    id: 1,
                    author:"author",
                    message:"some message",
                    article:mockFor(BlogArticle).createMock())])

        controller.params.id = 1
        controller.params.author = "new author"
        controller.params.message = "some new message"

        controller.update()

        assertEquals "Comment 1 updated!", mockFlash.message
        assertEquals "list", redirectArgs.action
    }

    void testUpdateNotFound() {

        def controller = newInstance()

        mockDomain(BlogComment, [])

        controller.params.id = 1

        controller.update()

        assertEquals "Comment not found with id 1", mockFlash.message
        assertEquals "edit", redirectArgs.action
        assertEquals 1, redirectArgs.id
    }

}