import com.sixfootplus.blog.ArticleStatus
import com.sixfootplus.blog.BlogArticle
import com.sixfootplus.blog.BlogComment
import com.sixfootplus.blog.BlogUser
import com.sixfootplus.blog.BlogRole
import com.sixfootplus.blog.RssParserJob
import com.sixfootplus.blog.SmsConfig

class BootStrap {

    def init = {servletContext ->

        // is data in DB already available?
        if (BlogUser.findByUsername('admin')) {
            log.info 'Database is initialized already!'
            return
        }

        // create sms config
        SmsConfig config = new SmsConfig(provider:"something", username:"something", password:"something", mobile:"KzQ5MTc2NDgxNTczNzM=")
        config.save()

        // create some user roles
        def adminRole = new BlogRole(name:'ADMIN')
        adminRole.save()
        def userRole = new BlogRole(name:'USER')
        userRole.save()

        // create first user (in admin-role)
        BlogUser user = new BlogUser (username:'admin', password:'geheim', 
                                      email:'grails@moscon.de')
        user.addToRoles(adminRole)                                  
        if ( !user.save() ) {
          log.error "SAVING OF USER FAILED:\n ${user.errors}"
          return
        }     

        // create an article (in admin-role)
        BlogArticle article = new BlogArticle()
        article.subject = 'We will rock you!'
        article.body = '''Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do
eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure
dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur
sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
      '''
        article.author = user
        article.save()
        if (article.hasErrors()) {
            log.error "SAVING OF ARTICLE FAILED:\n ${article.errors}"
            return
        }

        // create a comment for the article above
        //
        BlogComment comment = new BlogComment()
        comment.message = "Danke fuerr den Artikel. Mein konstruktives Feedback: Dies und das..."
        comment.article = article
        comment.author = "Guru"
        if (!comment.save()) {
            log.error "SAVING OF COMMENT FAILED:\n ${comment.errors}"
            return
        }

        // create some more dummy blog entries
        //
        for (int i = 0; i < 15; i++) {
            article = new BlogArticle()
            article.subject = 'Beitrag Nr.' + (i + 1)
            article.body = 'Dies ist ein Testeintrag mit der Nr.' + (i + 1)
            article.author = user
            article.status = ArticleStatus.PUBLISHED
            if (!article.save()) {
                log.error "SAVING OF ARTICLE FAILED:\n ${article.errors}"
                return
            }
            article.dateCreated = new Date() - i              // trick to fake the created Date

            i.times {x ->
                comment = new BlogComment()
                comment.message = "Test-Kommentar ${(i + 1)}.${x + 1}"
                comment.article = article
                comment.author = "Anon"
                if (!comment.save()) {
                    log.error "SAVING OF COMMENT FAILED:\n ${comment.errors}"
                    return
                }
            }
        }

        //add some dummy tags
        BlogArticle.findAll().each {
            it.addTag('techie')
        }

        log.info "Blog Domain Objects created!"
    }

    def destroy = { }
}