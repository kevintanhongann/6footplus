package com.sixfootplus.blog.controller

import org.codehaus.groovy.grails.commons.ConfigurationHolder as holder


class UploadController {

    def index = { }
    
    def upload = {
        
        def uploadFile = request.getFile('myFile')
        def uploadDir =  holder.config.upload.dir

        if(!uploadFile.empty) {
            def file = new File(uploadDir + uploadFile.getFileItem().getName())
            if(file.exists()){
                flash.message = 'File already exists! : \'' + file.getAbsolutePath() + "\'"
                redirect(action:'index')
                return
            }
            uploadFile.transferTo(file)
            flash.message = 'Successfully uploaded to : \'' + file.getAbsolutePath() + "\'"
            redirect(action:'index')
        } else {
            flash.message = 'File cannot be empty'
            redirect(action:'index')
        }
    }
    
}
