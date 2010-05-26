class UrlMappings {

    //    static mappings = {
    //     "/$controller/$action?/$id?"{
    //	      constraints {
    //			 // apply constraints here
    //		  }
    //	  }
    //     "/"(view:"/index")
    //	  "500"(view:'/error')
    //	}

    static mappings = {
        // startpage
        "/" (controller:'home', action:'index')

        // standard mapping of Grails
        "/$controller/$action?/$id?" {}

        "/$id?" {
            controller = "home"
            action = "show"
            constraints {
                id(matches:"[0-9]+")
            }
        }
        // error definition
        "500"(controller: 'error', action : 'error')
        "404"(controller: 'error', action : 'notfound')
    }
}
