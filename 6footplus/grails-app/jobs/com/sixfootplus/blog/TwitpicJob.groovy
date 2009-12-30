package com.sixfootplus.blog

import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat

class TwitpicJob {
    
    def concurrent = false
    def cronExpression = "0 0/1 * * * ?"
    def producer = "twitpic"
    
    
    def execute() {
        
        def url = new URL("http://twitpic.com/photos/6footplus/feed.rss")
        def reader = new BufferedReader(new InputStreamReader(url.openStream()))

        def buffer = new StringBuffer()
        def inputLine

        while ((inputLine = reader.readLine()) != null) {
              buffer.append(inputLine)
         }
         reader.close()
         
        def xml = buffer.toString()
        def pimped = xml.replace("<rss", "<!DOCTYPE rss [<!ENTITY Atilde \"&#195;\"><!ENTITY para   \"&#182;\">]><rss")
        
        def xmlFeed = new XmlParser().parseText(pimped)
                
        //delete existing only if rss contains items
        if(xmlFeed?.channel?.item != null){
            RssFeed.findAllByProducer(producer)*.delete();
        }
        
        (0..< xmlFeed.channel.item.size()).each {
        
            def item = xmlFeed.channel.item.get(it);
            def thumbnail = item.guid.text().replace(".com/", ".com/show/mini/")
            def formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z")
            def date = formatter.parse(item.pubDate.text())
        
            RssFeed feed = new RssFeed(producer:producer, title:item.title.text(), description:item.description.text(), link:item.link.text(), thumbnail:thumbnail, pubDate:date)
            feed.save()
        }
    }
    
}