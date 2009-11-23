package com.sixfootplus.blog.controller

import com.sixfootplus.blog.RssFeed

class RssReaderController {

    def show = {

        def result = RssFeed.findAllByProducer(params.producer, [max: 1, sort: "pubDate", order: "desc", offset: 0])
        
        if(result.isEmpty()){
            result = Collections.emptyList()
        }

        [feedList:result]
    }
    
}
