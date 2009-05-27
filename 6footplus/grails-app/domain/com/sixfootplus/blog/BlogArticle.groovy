package com.sixfootplus.blog

class BlogArticle {

    static searchable = true

    //properties
    String subject
    String body
    Date dateCreated
    Date lastUpdated
    ArticleStatus status = ArticleStatus.UNPUBLISHED

    //relations
    BlogUser author
    static hasMany = [comments: BlogComment, links: BlogLink]

    static constraints = {
        subject(blank: false, nullable: false, size: 5..200, unique: false)
        body(blank: false, nullable: false, size: 5..10000)
        status(nullable: false)
        author(nullable: false)
    }
}

enum ArticleStatus {
    UNPUBLISHED,
    PUBLISHED,
    DISABLED
}
