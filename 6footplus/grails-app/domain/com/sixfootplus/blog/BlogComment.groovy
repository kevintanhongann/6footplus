package com.sixfootplus.blog

import com.sixfootplus.blog.BlogArticle
import com.sixfootplus.blog.BlogUser

class BlogComment {

  String message
  Date dateCreated

  BlogUser user
  static belongsTo = [article: BlogArticle]

  static constraints = {
    message(blank: false, nullable: false, size: 5..1000)
    user(nullable: true)
  }

  String toString() {
    return "#${id}: ${message?.size() < 21 ? message : message[0..19]}"
  }
}
