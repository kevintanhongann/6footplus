package com.sixfootplus.blog

class TwitterJob {
    
    def concurrent = false
    def cronExpression = "0 0/1 * * * ?"
    def twitterService
    def mbCal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    
    def execute() {

        def messages = twitterService.getUserTimeline("6footplus", 10, null)
        
        if(!messages.isEmpty()) {
            TwitterStatus.findAll()*.delete();
            println "Deleted all existing twitter entries!"
        }

        (0..< messages.size()).each {

            def item = messages.get(it)
            def date = item.createdAt

            mbCal.setTimeInMillis(date.getTime())

            Calendar cal = Calendar.getInstance()
            cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR))
            cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH))
            cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH))
            cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY))
            cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE))
            cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND))
            cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND))

            def twitterStatus = new TwitterStatus(id:item.id, text:item.text, createdAt:cal.getTime())
            
            if(!twitterStatus.save()){
                println "Could not save twitter status : " + item.text;
            }
        }
    }
}
