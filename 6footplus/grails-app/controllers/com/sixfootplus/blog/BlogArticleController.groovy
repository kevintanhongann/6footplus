package com.sixfootplus.blog

import com.sixfootplus.blog.ArticleStatus
import com.sixfootplus.blog.BlogArticle
import com.sixfootplus.blog.BlogUser

class BlogArticleController {

    def index = { 
    
        redirect(action: list, params: params) 
    }

    def list = {

        Map model = [:]

        model.articles = BlogArticle.findAll(
                [max: 10,
                        offset: params.offset,
                        sort: 'dateCreated',
                        order: 'desc'])
        model.blogCount = BlogArticle.count()

        return model
    }

    def create = {

        BlogArticle article = new BlogArticle()

        render(view: 'create', model: [article: article, users: BlogUser.findAll(), ArticleStatus: ArticleStatus])
    }

    def save = {

        def article = new BlogArticle()
        article.properties = params
    
        if (article.save()) {
            flash.message = "Article ${article.id} created."
            redirect(action: list)
        } else {
            render(view: 'create', model: [article: article, users: BlogUser.findAll(), ArticleStatus: ArticleStatus])
        }
    }

    def edit = {

        def article = BlogArticle.get(params.id)

        if (!article) {
            flash.message = "Article not found with id ${params.id}"
            redirect(action: list)
        } else {
            return [article: article, ArticleStatus: ArticleStatus]
        }
    }

    def update = {

        def article = BlogArticle.get(params.id)

        if (article) {
            article.properties = params
            if (article.save()) {
                flash.message = "Article ${params.id} updated."
                redirect(action: show, id: article.id)
            } else {
                render(view: 'edit', model: [article: article])
            }
        } else {
            flash.message = "Article not found with id ${params.id}"
            redirect(action: edit, id: params.id)
        }
    }

    def show = {
        def article = BlogArticle.get(params.id)
        return [article: article]
    }

    def delete = {
        def article = BlogArticle.get(params.id)

        if (article) {
            article.delete()
            flash.message = "Article ${params.id} deleted."
            redirect(action: list)
        }
        else {
            flash.message = "Article not found with id ${params.id}"
            redirect(action: list)
        }
    }
}
