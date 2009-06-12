package com.sixfootplus.blog

import com.sixfootplus.blog.BlogArticle
import org.codehaus.groovy.grails.commons.ConfigurationHolder as holder

class FeedController {

    def max = 10
    def sort = "dateCreated"
    def order = "desc"

    def atom = {
        render(feedType: "atom") {
            title = "6footplus.com - ATOM"
            link = holder.config.grails.serverURL

            BlogArticle.list(max: max, sort: sort, order: order).each() {
                def article = it
                entry(it.subject) {
                    author = "Andreas Nerlich"
                    title = "${article.subject}"
                    link = host + "/home/show/${article.id}"
                    publishedDate = article.dateCreated
                    if(article.body.length() > 39){
                        article.body.substring(0, 40) + "..."
                    }
                }
            }
        }
    }

    def rss = {
        render(feedType:"rss", feedVersion:"2.0") {
            title = "6footplus.com - RSS"
            link = holder.config.grails.serverURL
            description = "blog entries by the 6footplus fella"

            BlogArticle.list(max: max, sort: sort, order: order).each() {
                def article = it
                entry(it.subject) {
                    author = "Andreas Nerlich"
                    title = "${article.subject}"
                    link = host + "/home/show/${article.id}"
                    publishedDate = article.dateCreated
                    if(article.body.length() > 39){
                        article.body.substring(0, 40) + "..."
                    }
                }
            }
        }
    }
}