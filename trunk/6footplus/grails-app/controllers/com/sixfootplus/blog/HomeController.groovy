package com.sixfootplus.blog

import com.sixfootplus.blog.ArticleStatus
import com.sixfootplus.blog.BlogArticle
import com.sixfootplus.blog.BlogComment

class HomeController {
    
    def index = {
        
        Map model = [:]
        
        def list
        def total

        if(!params.tag){
            if(!params.max){
                params.max = 3
            }
            list = BlogArticle.findAllByStatus(ArticleStatus.PUBLISHED,
                [max: params.max, offset: params.offset, sort: 'dateCreated',order: 'desc'])
            model.blogCount = BlogArticle.countByStatus(ArticleStatus.PUBLISHED)

        } else {

            if(!params.max){
                params.max = 10
            }
            //due to tag plugin findAllByTagAndStatus is not possible
            list = BlogArticle.findAllByTagWithCriteria(params.tag,  {
                    eq('status', ArticleStatus.PUBLISHED)
                    order("dateCreated", "desc")
                })

            total = list.size

            def max = params.max as int
            def offset = ((params.offset as int) ?: 0)

            list = list[offset..(((offset + max) > total) ? total : (offset + max)) - 1]
        }
        
        model.articles = list
        model.blogCount = total

        return model
    }
    
    def recent = {

        def articles = BlogArticle.findAllByStatus(ArticleStatus.PUBLISHED,
            [max: params.maxRecent ?: 4,
                sort: 'dateCreated',
                order: 'desc'])
        
        return [articles:articles]
    }

    def show = {

        def article = BlogArticle.get(params.id)
        if(article){
            return [article: article]
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
        
        //load article
        BlogArticle article = BlogArticle.read(params.articleId)
        
        BlogComment comment = new BlogComment()
        comment.message = params.message
        comment.article = article
        comment.ip = request.getRemoteAddr()
        comment.author = params.author
                
        if (!comment.save()) {
            model.newComment = comment
        } else {
            flash.message = "Thank you for your comment"
        }
        
        model.showComments = true
        model.article = article
        
        render(template: 'comments', model: model)
    }
}
