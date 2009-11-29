package com.sixfootplus.blog.controller

import com.sixfootplus.blog.*
import com.sixfootplus.blog.service.TagService


class BlogArticleControllerTests extends grails.test.ControllerUnitTestCase {

    void testIndex() {

        def controller = newInstance()

        mockDomain(BlogArticle)

        controller.index()

        assertEquals "list", redirectArgs.action
    }

    //    TODO findAll with params mock not working yet

    //    void testList() {
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
    //        def model = controller.list()
    //
    //        assertNotNull model.articles
    //        assertNotNull model.blogCount
    //
    //        assertTrue model.articles instanceof List
    //        assertTrue model.articles[0] instanceof BlogArticle
    //
    //        assertEquals 1, model.blogCount
    //        assertEquals 1, model.articles.size()
    //    }

    void testCreate() {

        def controller = newInstance()

        mockDomain(BlogUser, [new BlogUser(
                    username:"username",
                    password:"password",
                    email:"email")])

        def model = controller.create()

        assertEquals "create", renderArgs.view

        assertNotNull renderArgs.model.article
        assertNotNull renderArgs.model.users
        assertNotNull renderArgs.model.ArticleStatus
    }

    void testSave() {

        def controller = newInstance()

        mockDomain(BlogArticle)
        mockDomain(BlogUser, [new BlogUser(
                    username:"username",
                    password:"password",
                    email:"email")])

        controller.params.subject = "subject subject"
        controller.params.body = "body body body"
        controller.params.status = ArticleStatus.PUBLISHED
        controller.params.author = [
            id:1,
            username:"username",
            password:"password",
            email:"user@name.com"]

        controller.save()

        assertEquals "list", redirectArgs.action
        assertEquals "Article 1 created.", mockFlash.message
    }

    void testSaveInvalid() {

        def controller = newInstance()

        mockDomain(BlogArticle)
        mockDomain(BlogUser, [new BlogUser(
                    username:"username",
                    password:"password",
                    email:"email")])

        controller.params.subject = "s" //subject length not valid
        controller.params.body = "body body body"
        controller.params.status = ArticleStatus.PUBLISHED
        controller.params.author = [
            id:1,
            username:"username",
            password:"password",
            email:"user@name.com"]

        def model = controller.save()

        assertEquals "create", renderArgs.view

        assertNotNull renderArgs.model.article
        assertNotNull renderArgs.model.users
        assertNotNull renderArgs.model.ArticleStatus
    }

    void testEdit() {

        def controller = newInstance()

        mockDomain(BlogArticle, [new BlogArticle(
                    id:1,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        controller.params.id = 1

        def model = controller.edit()

        assertNotNull model.article
        assertNotNull model.ArticleStatus
    }

    void testEditNoFound() {

        def controller = newInstance()

        mockDomain(BlogArticle, [new BlogArticle(
                    id:1,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        controller.params.id = 10

        def model = controller.edit()

        assertEquals "list", redirectArgs.action
        assertEquals "Article not found with id 10", mockFlash.message
    }

    void testShow() {

        def controller = newInstance()

        mockDomain(BlogArticle, [new BlogArticle(
                    id:1,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        controller.params.id = 1

        def model = controller.show()

        assertNotNull model.article
    }

    void testShowNotFound() {

        def controller = newInstance()

        mockDomain(BlogArticle, [new BlogArticle(
                    id:1,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        controller.params.id = 10

        controller.show()

        assertEquals "Article not found with id 10", mockFlash.message
    }

    void testUpdate() {

        def controller = newInstance()

        mockDomain(BlogArticle, [new BlogArticle(
                    id:1,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        controller.params.id = 1

        controller.update()

        assertEquals "Article 1 updated.", mockFlash.message
        assertEquals "show", redirectArgs.action
        assertEquals 1, redirectArgs.id
    }

    void testUpdateInvalid() {

        def controller = newInstance()

        mockDomain(BlogArticle, [new BlogArticle(
                    id:1,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        controller.params.id = 1
        controller.params.subject = ""

        controller.update()

        assertEquals "edit", renderArgs.view
        assertNotNull renderArgs.model.article
    }

    void testUpdateNotFound() {

        def controller = newInstance()

        mockDomain(BlogArticle, [new BlogArticle(
                    id:1,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        controller.params.id = 10

        controller.update()

        assertEquals "Article not found with id 10", mockFlash.message
        assertEquals "edit", redirectArgs.action
        assertEquals 10, redirectArgs.id
    }

    void testDelete() {

        def controller = newInstance()

        mockDomain(BlogArticle, [new BlogArticle(
                    id:1,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        controller.params.id = 1

        def model = controller.delete()

        assertEquals "list", redirectArgs.action
        assertEquals "Article 1 deleted.", mockFlash.message
    }

    void testDeleteNotFound() {

        def controller = newInstance()

        mockDomain(BlogArticle)

        controller.params.id = 1

        controller.delete()

        assertEquals "list", redirectArgs.action
        assertEquals "Article not found with id 1", mockFlash.message
    }

    void testSaveAjaxTag() {

        def tagServiceMock = mockFor(TagService)
        tagServiceMock.demand.parseTags {article, tag -> return}

        def controller = newInstance()
        controller.tagService = tagServiceMock.createMock()

        def article = new BlogArticle(
            id:1,
            subject:"subject subject",
            body:"body body body",
            status:ArticleStatus.PUBLISHED,
            author:mockFor(BlogUser).createMock())

        def blogArticleMock = mockFor(BlogArticle)
        blogArticleMock.demand.static.get {id -> return article}
        blogArticleMock.createMock()

        controller.params.articleId = 1
        controller.params.tag = "newtag"

        controller.ajaxSaveTag()

        assertEquals "tags", renderArgs.template
        assertNotNull renderArgs.model.article
    }

    void testDeleteAjaxTag() {

        def tagServiceMock = mockFor(TagService)
        tagServiceMock.demand.removeTag {article, tag -> return}

        def controller = newInstance()
        controller.tagService = tagServiceMock.createMock()

        mockDomain(BlogArticle, [new BlogArticle(
                    id:1,
                    subject:"subject subject",
                    body:"body body body",
                    status:ArticleStatus.PUBLISHED,
                    author:mockFor(BlogUser).createMock())])

        controller.params.id = 1
        controller.params.tag = "tag"

        controller.ajaxDeleteTag()

        assertEquals "tags", renderArgs.template
        assertNotNull renderArgs.model.article
    }

}
