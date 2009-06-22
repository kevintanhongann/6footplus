package com.sixfootplus.blog

import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat


class RssParserJob {
    
    def concurrent = false
    def cronExpression = "0 0/1 * * * ?"
    def feeds = [
        vimeo:"http://vimeo.com/sixfootplus/videos/rss",
        flickr:"http://api.flickr.com/services/feeds/photos_public.gne?id=26648553@N05&lang=en-us&format=rss_200"]
    
    def execute() {
        
        feeds.each{ mapFeed ->

            def xmlFeed = new XmlParser().parse(mapFeed.value)
            def ns = new groovy.xml.Namespace("http://search.yahoo.com/mrss/", 'media')

            //delete existing only if rss contains items
            if(xmlFeed?.channel?.item != null){
                RssFeed.findAllByProducer(mapFeed.key)*.delete();
                println "Deleted all existing ${mapFeed.key} entries!"
            }
            
            (0..< xmlFeed.channel.item.size()).each {
            
                def item = xmlFeed.channel.item.get(it);
                def thumbnail = item[ns.content][ns.thumbnail].'@url'?.text()
                def description
                
                if(!thumbnail) {
                    thumbnail = item[ns.thumbnail].'@url'?.text()
                }
                
                if(!thumbnail) {
                    description = item.description.text()
                    thumbnail = description.substring(description.indexOf("src="), description.length())
                }
                
                description = item.description.text().size() < 255 ? item.description.text() : item.description.text()[0..254]
                
                def formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z")
                def date = formatter.parse(item.pubDate.text())
            
                RssFeed feed = new RssFeed(producer:mapFeed.key, title:item.title.text(), description:description, link:item.link.text(), thumbnail:thumbnail, pubDate:date)
                
                if(!feed.save()){
                    println "Could not save " + mapFeed.key + " rss item : " + item.title;
                }
            }
        }
    }
    
}