package com.sixfootplus.blog

import com.sixfootplus.blog.BlogComment
import com.sixfootplus.blog.BlogUser

class BlogArticle {

    static searchable = true

    //properties
    String subject
    String body
    ArticleStatus status = ArticleStatus.UNPUBLISHED
    Date dateCreated
    Date lastUpdated

    //relations
    BlogUser author
    static hasMany = [comments: BlogComment]

    static constraints = {
        subject(blank: false, nullable: false, size: 5..200, unique: true)
        body(blank: false, nullable: false, size: 5..10000)
        status(nullable: false)
        author(nullable: false)
    }
}

enum ArticleStatus {
    UNPUBLISHED,
    PUBLISED,
    DISABLED
}
