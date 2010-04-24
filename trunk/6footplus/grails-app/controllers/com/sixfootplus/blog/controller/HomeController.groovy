package com.sixfootplus.blog.controller

import com.sixfootplus.blog.ArticleStatus
import com.sixfootplus.blog.BlogArticle
import com.sixfootplus.blog.BlogComment

class HomeController {
    
    def index = {
        
        Map model = [:]
        
        def list = []
        def total

        if(!params.tag){

            flash.message = ""

            if(!params.max){
                params.max = 3
            }

            list = BlogArticle.findAllByStatus(ArticleStatus.PUBLISHED,
                [max: params.max,
                    offset: params.offset ?: 0,
                    sort: 'dateCreated',
                    order: 'desc'])
            total = BlogArticle.countByStatus(ArticleStatus.PUBLISHED)

            if(total < 1){
                flash.message = "No articles found!"
            } 

        } else {

            if(!params.max){
                params.max = 10
            }
            //due to tag plugin findAllByTagAndStatus is not possible
            list = BlogArticle.findAllByTagWithCriteria(params.tag,  {
                    eq('status', ArticleStatus.PUBLISHED)
                    order("dateCreated", "desc")
                })
            total = list.size()

            def max = params.max as int
            def offset = ((params.offset as int) ?: 0)

            if(total > 0){
                list = list[offset..(((offset + max) > total) ? total : (offset + max)) - 1]
                flash.message = "Showing only articles tagged as '" + params.tag + "'"
            } else {
                flash.message = "No articles found for tag '${params.tag}'"
            }
        }
        
        model.articles = list
        model.blogCount = total

        return model
    }
    
    def recent = {

        def offsetRecent = params.offsetRecent ?: 3
        int offset = (params.offset ?: 0).toInteger() + offsetRecent.toInteger()

        def articles = BlogArticle.findAllByStatus(ArticleStatus.PUBLISHED,
            [max: params.maxRecent ?: 5,
                offset: offset,
                sort: 'dateCreated',
                order: 'desc'])

        return [articles:articles]
    }

    def show = {

        if(!params.id.isInteger()) {
            flash.message = "Article id '${params.id}' is not valid!"
            return
        }

        def article = BlogArticle.get(params.id)
        if(article){
            flash.message = ""
            def after = BlogArticle.findByDateCreatedGreaterThanAndStatus(
                                article.dateCreated,
                                ArticleStatus.PUBLISHED,
                                [max: 1, sort: 'dateCreated', order: 'asc'])
            def before = BlogArticle.findByDateCreatedLessThanAndStatus(
                                article.dateCreated,
                                ArticleStatus.PUBLISHED,
                                [max: 1, sort: 'dateCreated', order: 'desc'])
            return [article: article, before:before, after:after]
        } else {
            flash.message = "Article not found with id ${params.id}"
        }
    }

    def ajaxShowComments = {

        def article = BlogArticle.read(params.id)
        render(template: 'comments', model: [article: article, showComments: true])
    }
    
    def ajaxSaveComment = {

        //flush any existing flash messages
        flash.message = null
        
        //initialize return object
        Map model = [:]

        //is captcha answer correct?
        def captchaValid = params.captcha == session.captcha
        
        //load article
        BlogArticle article = BlogArticle.read(params.articleId)

        //instantiate comment entity
        BlogComment comment = new BlogComment()
        comment.message = params.message
        comment.article = article
        comment.ip = request.getRemoteAddr()
        comment.author = params.author

        if (comment.validate() && captchaValid) {
            comment.save()
            flash.message = "Thank you for your comment"
        } else {
            if(!captchaValid) {
                comment.errors.rejectValue('captcha', null, "Incorrect Maths addition answer")
            }
            model.newComment = comment
        }
        
        model.showComments = true
        model.article = article
        
        render(template: 'comments', model: model)
    }
}
