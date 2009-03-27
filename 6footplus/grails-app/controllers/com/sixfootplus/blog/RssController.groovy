package com.sixfootplus.blog

import com.sixfootplus.blog.BlogArticle

class RssController {

    def feed = {
        render(feedType: "atom") { // optional - , feedVersion:"2.0") {
            title = "6footplus.com List"
            link = "http://localhost:8080/6footplus/rss"

            BlogArticle.list(sort: "subject", order: "asc").each() {
                def article = it
                entry(it.subject) {
                    title = "${article.subject}"
                    link = "http://localhost:8080/6footplus/blogArticle/show/${article.id}"
                    author = "${article.author.username}, ${article.author.password}"
                    publishedDate = article.dateCreated
                }
            }
        }
    }
}
