package com.sixfootplus.blog

import com.sixfootplus.blog.TwitterStatus

class TwitterController {

    def list = {
        
        def result = TwitterStatus.findAll([max: params.maxTweets, sort: "createdAt", order:"desc"])
        
        if(result.isEmpty()){
            result = Collections.emptyList()
        }

        [result:result]
    }
    
    def show = {
        
        def result = TwitterStatus.findAll([max: 1, sort: "createdAt", order:"desc"])
        
        def status
        
        if(!result.isEmpty()){
            status = result.get(0)
        }

        [status:status]
    }
}