package com.sixfootplus.blog

import com.sixfootplus.blog.BlogArticle
import org.codehaus.groovy.grails.commons.ConfigurationHolder as holder

class FeedController {

    def host = holder.config.grails.serverURL

    def atom = {
        render(feedType: "atom") {
            title = "6footplus.com - ATOM"
            link = host

            BlogArticle.list(sort: "dateCreated", order: "desc").each() {
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
            link = host
            description = "blog entries by the 6footplus fella"

            BlogArticle.list(sort: "dateCreated", order: "desc").each() {
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