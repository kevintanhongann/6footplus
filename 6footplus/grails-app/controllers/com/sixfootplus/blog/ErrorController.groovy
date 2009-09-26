package com.sixfootplus.blog

class ErrorController {

    def redirects = ["/blog.6", "/index.html", "/index.jsp"]

    def index = { 
        redirect(action:error)
    }

    def error = {}

    def notfound = {
        def uri = request.'javax.servlet.error.request_uri'
        if(redirects.find{it == uri}){
            redirect(controller: "home", action: "index")
        } else {
            render(view:"/error/404")
        }
    }
}
