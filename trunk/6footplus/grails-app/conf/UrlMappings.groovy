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

        // error definition
        "500"(view:'/error/error')
        
        "404"(view:'/error/404')
    }

}
