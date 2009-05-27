package com.sixfootplus.blog

class RssFeed {

    String producer
    String title
    String description
    String link
    String thumbnail
    Date pubDate

    static constraints = {
    }
    
    String toString() { "${producer}, ${title}, ${description}, ${link}, ${thumbnail}, ${pubDate}" }
    
}
