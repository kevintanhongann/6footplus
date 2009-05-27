package com.sixfootplus.blog

import com.sixfootplus.blog.BlogArticle

import org.jsecurity.crypto.hash.Sha1Hash

class BlogUser {

  String  username
  String  password
  String  email
  
  static hasMany = [articles:BlogArticle, roles:BlogRole]
                    
  static constraints = {
    username    (blank:false, nullable:false, size:3..30, unique:true, matches:"[a-zA-Z]+")
    password    (blank:false, nullable:false, size:40..40)
    email       (email:true)        
  } 
  
  // quick and dirty:  forcing SHA-1 encoding 
  void setPassword (String passwd) {
    if (passwd && !(passwd ==~ /[\da-fA-F]{40}/)) {
        this.password = new Sha1Hash(passwd).toHex()
    } else {
        this.password = passwd
    }
  }
  
  String toString() {
    return "#${id}: ${username}"   
  }                  

}
