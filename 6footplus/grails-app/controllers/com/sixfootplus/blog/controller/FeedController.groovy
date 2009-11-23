package com.sixfootplus.blog.controller

import com.sixfootplus.blog.BlogArticle
import org.codehaus.groovy.grails.commons.ConfigurationHolder as holder

class FeedController {

    def max = 10
    def sort = "dateCreated"
    def order = "desc"
    def host = holder.config?.grails?.serverURL

    def atom = {
        
        render(feedType: params.type ?: "atom") {

            title = "6footplus.com"
            description = "Latest ${max} articles by Andreas Nerlich"
            link = host

            def list

            if(!params.id){
                list = BlogArticle.findAllByStatus(ArticleStatus.PUBLISHED, [max: max, sort: sort, order: order])}
            else {
                list = BlogArticle.findAllByTagWithCriteria(params.id,  {
                        eq('status', ArticleStatus.PUBLISHED)
                        order(sort, order)
                    })
                description = "Articles by Andreas Nerlich tagged as '$params.id'"
            }

            list.each() {
                def article = it
                entry(it.subject) {
                    author = "Andreas Nerlich"
                    title = "${article.subject}"
                    link = host + "/home/show/${article.id}"
                    publishedDate = article.dateCreated
                    article.body
                }
            }
        }
    }

    def rss = {

        redirect(action:atom, params:["type":"rss", "id":params.id])
    }
}