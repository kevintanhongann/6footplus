package com.sixfootplus.blog

import com.sixfootplus.blog.BlogArticle
import org.codehaus.groovy.grails.commons.ConfigurationHolder as holder

class FeedController {

    def max = 10
    def sort = "dateCreated"
    def order = "desc"
    def host = holder.config?.grails?.serverURL

    def atom = {
        render(feedType: "atom") {
            title = "6footplus.com - blog"

            BlogArticle.list(max: max, sort: sort, order: order).each() {
                def article = it
                entry(it.subject) {
                    author = "Andreas Nerlich"
                    title = "${article.subject}"
                    link = host + "/home/show/${article.id}"
                    publishedDate = article.dateCreated
                    if(article.body.length() < 128){
                        article.body
                    }
                }
            }
        }
    }

    def rss = {
        render(feedType:"rss", feedVersion:"2.0") {
            title = "6footplus.com - blog"
            description = "personal blog entries by andreas nerlich"
            link = host

            BlogArticle.list(max: max, sort: sort, order: order).each() {
                def article = it
                entry(it.subject) {
                    author = "Andreas Nerlich"
                    title = "${article.subject}"
                    link = host + "/home/show/${article.id}"
                    publishedDate = article.dateCreated
                    if(article.body.length() < 128){
                        article.body
                    }
                }
            }
        }
    }
}