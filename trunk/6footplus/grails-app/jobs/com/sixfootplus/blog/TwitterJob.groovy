package com.sixfootplus.blog

import twitterChecker.*

class TwitterJob {

	def twitterCheckerService
	
    def concurrent = false
    def cronExpression = "0 0/15 * * * ?"
    def twitterService
    def mbCal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    
    def execute() {

        def messages = twitterCheckerService.userTimeline
        
        if(!messages.isEmpty()) {
            TwitterStatus.findAll()*.delete();
        }

        messages.each {

            def date = it.createdAt

            mbCal.setTimeInMillis(date.getTime())

            Calendar cal = Calendar.getInstance()
            cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR))
            cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH))
            cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH))
            cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY))
            cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE))
            cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND))
            cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND))

            def twitterStatus = new TwitterStatus(id:it.id, text:it.text, createdAt:cal.getTime())
            
            if(!twitterStatus.save()){
                println "Could not save twitter status : " + it.text;
            }
        }
    }
}
