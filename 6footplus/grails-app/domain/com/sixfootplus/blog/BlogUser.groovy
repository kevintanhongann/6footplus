package com.sixfootplus.blog

import com.sixfootplus.blog.BlogArticle
import com.sixfootplus.blog.BlogComment

class BlogUser {

  String username
  String password
  String email

  static hasMany = [comments: BlogComment,
          articles: BlogArticle]

  static constraints = {
    username(blank: false, nullable: false, size: 3..30, unique: true, matches: "[a-zA-Z]+")
    password(blank: false, nullable: false, size: 3..30)
    email(email: true)
  }


  String toString() {
    return "#${id}: ${username}"
  }

}
