package com.sixfootplus.blog

class SmsConfig {

    String username
    String password
    String provider
    String mobile

    static constraints = {
        username(blank: false, nullable: false, size: 1..64)
        password(blank: false, nullable: false, size: 1..64)
        provider(blank:false, nullable: false, size: 1..64)
        mobile(blank:false, nullable: false, size: 1..32)
    }
}
