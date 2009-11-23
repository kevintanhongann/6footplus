package com.sixfootplus.blog.controller

import com.sixfootplus.blog.*

class HomeControllerTests extends grails.test.ControllerUnitTestCase {

// Enable once latest release supports mock for countyBy (since 1.2-M3)

//    void testIndex() {
//
//        def controller = newInstance()
//
//        mockDomain(BlogArticle, [new BlogArticle(
//                    id:10L,
//                    subject:"subject subject",
//                    body:"body body body",
//                    status:ArticleStatus.PUBLISHED,
//                    author:mockFor(BlogUser).createMock())])
//
//        def model = controller.index()
//
//        assertNotNull model.articles
//        assertNotNull model.blogCount
//
//        assertTrue model.articles instanceof List
//        assertTrue model.articles[0] instanceof BlogArticle
//
//        assertEquals 1, model.blogCount
//        assertEquals 1, model.articles.size()
//
//        // do another simple run throw with conditional params set
//        controller.params.max = "1"
//        controller.params.offset = "0"
//
//        model = controller.index()
//
//        assertNotNull model.articles
//        assertNotNull model.blogCount
//    }
//
//    void testIndexWithNoArticles() {
//
//        def controller = newInstance()
//
//        mockDomain(BlogArticle, [])
//
//        def model = controller.index()
//
//        assertNotNull model.articles
//        assertNotNull model.blogCount
//
//        assertTrue model.articles instanceof List
//
//        assertEquals 0, model.blogCount
//        assertEquals 0, model.articles.size()
//
//        assertEquals "No articles found!", mockFlash.message
//    }

    void testIndexWithTag() {

        def controller = newInstance()
        controller.params.tag = "dev"

        def article = mockFor(BlogArticle)
        article.demand.static.findAllByTagWithCriteria {tag, closure ->
            return [new BlogArticle(
                    id:10L,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())] }

        def model = controller.index()
        
        assertNotNull model.articles
        assertNotNull model.blogCount

        assertTrue model.articles instanceof List

        assertEquals 1, model.blogCount
        assertEquals 1, model.articles.size()
    }

    void testIndexWithTagNoArticles() {

        def controller = newInstance()
        controller.params.tag = "dev"

        def article = mockFor(BlogArticle)
        article.demand.static.findAllByTagWithCriteria {tag, closure -> return [] }

        def model = controller.index()

        assertNotNull model.articles
        assertNotNull model.blogCount

        assertTrue model.articles instanceof List

        assertEquals 0, model.blogCount
        assertEquals 0, model.articles.size()

        assertEquals "No articles found for tag '" + controller.params.tag + "'", mockFlash.message
    }

    void testRecent() {

        def controller = newInstance()
        controller.params.offsetRecent = "0"

        mockDomain(BlogArticle, [new BlogArticle(
                    id:11L,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        def model = controller.recent()

        assertNotNull model.articles
        assertTrue model.articles instanceof List
        assertEquals 1, model.articles.size()

        //do again with params set
        controller.params.offset = "0"
        controller.params.maxRecent = "1"

        model = controller.recent()

        assertNotNull model.articles
    }

    void testShow() {

        def controller = newInstance()
        controller.params.id = "11"

        mockDomain(BlogArticle, [new BlogArticle(
                    id:9,
                    dateCreated: new Date() - 2,
                    subject:"subject subject 9",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock()),
                new BlogArticle(
                    id:10,
                    dateCreated: new Date() - 1,
                    subject:"subject subject 10",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock()),
                new BlogArticle(
                    id:11,
                    dateCreated: new Date(),
                    subject:"subject subject 11",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock()),
                new BlogArticle(
                    id:12,
                    dateCreated: new Date() + 1,
                    subject:"subject subject 12",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock()),
                new BlogArticle(
                    id:13,
                    dateCreated: new Date() + 2,
                    subject:"subject subject 13",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        def model = controller.show()

        assertNotNull model.article
        assertNotNull model.before
        assertNotNull model.after

        assertEquals 11, model.article.id
        assertEquals 10, model.before.id
        assertEquals 12, model.after.id
    }

    void testShowWithNoArticles() {

        def controller = newInstance()
        controller.params.id = "11"

        mockDomain(BlogArticle, [])

        def model = controller.show()

        assertEquals "Article not found with id " + controller.params.id, mockFlash.message
    }


    void testAjaxShowComments() {

        def controller = newInstance()
        controller.params.id = "9"

        mockDomain(BlogArticle, [new BlogArticle(
                    id:9,
                    subject:"subject subject 9",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        def model = controller.ajaxShowComments()

        assertNotNull model.article

        assertEquals "comments", renderArgs.template
        assertEquals 9, model.article.id
        assertTrue model.showComments
    }

    void testAjaxSaveComment() {

        def controller = newInstance()
        controller.session.captcha = "9"
        controller.params.captcha = "9"
        controller.params.articleId = "11"
        controller.params.message = "some message"
        controller.params.author = "some author"

        mockDomain(BlogArticle, [new BlogArticle(
                    id:11,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])
        mockDomain(BlogComment, [new BlogComment(message: "some message", author: "some author")])

        def model = controller.ajaxSaveComment()

        assertNotNull model.article

        assertEquals "comments", renderArgs.template
        assertEquals 11, model.article.id
        assertTrue model.showComments

        assertEquals "Thank you for your comment", mockFlash.message
    }

    void testAjaxSaveCommentInvalidCaptcha() {

        def controller = newInstance()
        controller.session.captcha = "1000"
        controller.params.captcha = "9"
        controller.params.articleId = "11"
        controller.params.message = "some message"
        controller.params.author = "some author"

        mockDomain(BlogArticle, [new BlogArticle(
                    id:11,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])
        mockDomain(BlogComment, [new BlogComment(message: "some message", author: "some author")])

        def model = controller.ajaxSaveComment()

        assertNotNull model.article
        assertNotNull model.newComment
        assertTrue model.showComments

        assertEquals "comments", renderArgs.template
        assertEquals 11, model.article.id
        assertEquals "some message", model.newComment.message
        assertEquals "some author", model.newComment.author
        assertNotNull model.newComment.errors

        def error = model.newComment.getErrors().getFieldError()

        assertEquals "captcha", error.getField()
        assertEquals "Incorrect Maths addition answer", error.getDefaultMessage()
    }

    void testAjaxSaveCommentInvalidComment() {

        def controller = newInstance()
        controller.session.captcha = "10"
        controller.params.captcha = "10"
        controller.params.articleId = "11"
        controller.params.message = "some message"
        controller.params.author = ""

        mockDomain(BlogArticle, [new BlogArticle(
                    id:11,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])
        mockDomain(BlogComment, [new BlogComment(message: "", author: "")])

        def model = controller.ajaxSaveComment()

        assertNotNull model.article
        assertNotNull model.newComment
        assertTrue model.showComments

        assertEquals "comments", renderArgs.template
        assertEquals 11, model.article.id
        assertEquals "some message", model.newComment.message
        assertEquals "", model.newComment.author

        assertNotNull model.newComment.errors

        def error = model.newComment.getErrors().getFieldError()

        assertEquals "author", error.getField()
    }
}