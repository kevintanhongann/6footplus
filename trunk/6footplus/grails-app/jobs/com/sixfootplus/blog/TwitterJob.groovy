package com.sixfootplus.blog


class TwitterJob {
    
    def concurrent = false
    def cronExpression = "0 0/1 * * * ?"
    def twitterService
    
    def execute() {

        def messages = twitterService.getUserTimeline("6footplus", 10, null)
        
        if(!messages.isEmpty()) {
            TwitterStatus.findAll()*.delete();
            println "All twitter statuses deleted!"
        }

         (0..< messages.size()).each {

                def item = messages.get(it)
                def twitterStatus = new TwitterStatus(id:item.id, text:item.text, createdAt:item.createdAt)
                twitterStatus.save()
                println "Saved twitter status!"
        }
    }
}
