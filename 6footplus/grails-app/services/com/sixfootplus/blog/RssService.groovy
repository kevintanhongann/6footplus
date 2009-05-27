package com.sixfootplus.blog

class RssService {

    boolean transactional = true

    def serviceMethod() {
        
        
        def xmlFeed = new XmlParser().parse("http://vimeo.com/sixfootplus/videos/rss");
        def feedList = []

        (0..0).each {

            def item = xmlFeed.channel.item.get(it);
            def ns = new groovy.xml.Namespace("http://search.yahoo.com/mrss/", 'media')
            def thumbnail = item[ns.content][ns.thumbnail].'@url'?.text()
            
            RssFeed feed = new RssFeed(title:item.title.text(), description:item.description.text(), link:item.link.text(), thumbnail:thumbnail)
            feedList << feed
        }

        [feedList:feedList]
        

    }
}
