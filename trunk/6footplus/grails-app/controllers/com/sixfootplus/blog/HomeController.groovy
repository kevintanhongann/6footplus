package com.sixfootplus.blog

import com.sixfootplus.blog.ArticleStatus
import com.sixfootplus.blog.BlogArticle
import com.sixfootplus.blog.BlogComment

class HomeController {
    
    def index = {
        
        Map model = [:]
        model.articles = BlogArticle.findAllByStatus(ArticleStatus.PUBLISHED,
                [max: 1,
                        offset: params.offset,
                        sort: 'dateCreated',
                        order: 'desc'])
        model.blogCount = BlogArticle.countByStatus(ArticleStatus.PUBLISHED)
        return model
    }
    
    def show = {
        def article = BlogArticle.get(params.id)
        return [article: article]
    }

    def ajaxShowComments = {

        def ba = BlogArticle.read(params.id)
        render(template: 'comments', model: [article: ba, showComments: true])
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