package com.sixfootplus.blog.controller

import com.sixfootplus.blog.BlogArticle
import com.sixfootplus.blog.ArticleStatus

import org.codehaus.groovy.grails.commons.ConfigurationHolder as holder

class FeedController {

    def max = 10
    def sort = "dateCreated"
    def order = "desc"
    def host = holder.config?.grails?.serverURL

    def rss = {

        render(feedType:"rss", feedVersion:"2.0") {

            title = "6footplus.com"
            link = host
            description = "Latest ${max} articles by Andreas Nerlich"

            def list

            if(!params.id){
                list = BlogArticle.findAllByStatus(ArticleStatus.PUBLISHED, [max: max, sort: sort, order: order])
            } else {
                list = BlogArticle.findAllByTagWithCriteria(params.id,  {
                        eq('status', ArticleStatus.PUBLISHED)
                        order(sort, order)
                    })
                description = "Articles by Andreas Nerlich tagged as '$params.id'"
            }

            list.each() { article ->
                entry(article.subject) {
                    author = "Andreas Nerlich"
                    title = "${article.subject}"
                    link = host + "/home/show/${article.id}"
                    publishedDate = article.dateCreated
                    article.body
                }
            }
        }
    }
}