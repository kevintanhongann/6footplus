package com.sixfootplus.blog

import com.sixfootplus.blog.BlogUser

class BlogRole {

    String name
    
    static hasMany = [users:BlogUser]
    static belongsTo = BlogUser

    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }
}
