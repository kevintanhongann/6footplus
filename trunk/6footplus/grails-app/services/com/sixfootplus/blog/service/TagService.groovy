package com.sixfootplus.blog.service

import com.sixfootplus.blog.BlogArticle

class TagService {

    boolean transactional = true

    def parseTags(BlogArticle article, String tags) {
        article.parseTags(tags)
    }

    def removeTag(BlogArticle article, String tag) {
        article.removeTag(tag)
    }
}
