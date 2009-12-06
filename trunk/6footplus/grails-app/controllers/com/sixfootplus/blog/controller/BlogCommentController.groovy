package com.sixfootplus.blog.controller

import com.sixfootplus.blog.BlogComment
import com.sixfootplus.blog.BlogArticle

class BlogCommentController {

    def scaffold = BlogComment

    def list = {

        Map model = [:]

        model.comments = BlogComment.findAll(
            [max: 10,
                offset: params.offset,
                sort: params.sort ?: 'dateCreated',
                order: params.order ?: 'desc'])
        model.commentCount = BlogComment.count()

        return model
    }


    def edit = {

        def comment = BlogComment.get(params.id)
        def articles = BlogArticle.findAll(
            [sort: 'dateCreated',
                order: 'desc'])

        if (!comment) {
            flash.message = "Comment not found with id ${params.id}"
            redirect(action: list)
        } else {
            return [comment: comment, articles: articles]
        }
    }

    def update = {

        def comment = BlogComment.get(params.id)

        if (comment) {
            comment.properties = params
            if (comment.save()) {
                flash.message = "Comment ${params.id} updated!"
                redirect(action: "list")
            } else {
                render(view: 'edit', model: [comment: comment, articles: BlogArticle.findAll(
                            [sort: 'dateCreated',
                                order: 'desc'])])
            }
        } else {
            flash.message = "Comment not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }


}