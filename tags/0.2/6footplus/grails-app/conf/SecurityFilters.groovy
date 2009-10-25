class SecurityFilters {
    
    def filters = {
        
        def protectedControllers = ['blogArticle', 'blogComment', 'blogUser', 'blogLink', 'upload']
    
        auth(controller: "*", action: "*") {
            
            before = {
                
                for ( protectedController in protectedControllers ){
                    if(protectedController.equals(controllerName)){
                        accessControl {
                            role("ADMIN")
                        }
                    }
                }
                
                //everything else is unprotected
                return true
            }
        }
    }
 }