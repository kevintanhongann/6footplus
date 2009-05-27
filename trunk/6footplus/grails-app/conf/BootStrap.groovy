import com.sixfootplus.blog.ArticleStatus
import com.sixfootplus.blog.BlogArticle
import com.sixfootplus.blog.BlogComment
import com.sixfootplus.blog.BlogUser
import com.sixfootplus.blog.BlogRole
import com.sixfootplus.blog.RssParserJob

class BootStrap {

    def init = {servletContext ->
    
        // is data in DB already available?
        //
        if (BlogUser.findByUsername('admin')) {
            log.info 'Database is initialized already!'
            return
        }

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
        //
        BlogArticle article = new BlogArticle()
        article.subject = 'Java Magazin bringt Grails Tutorial'
        article.body = '''Es ist soweit:
  In der neusten Ausgabe des Java Magazins findet sich der erste Teil eines Grails Tutorials.
  Insgesamt wird es zwei weitere Teile geben.
  Derr aufmerksame Leser wird am Ende des Tutorial in der Lage sein, selber professionelle
  Grails Applikationen zu schreiben!
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
        comment.message = "Danke für den Artikel. Mein konstruktives Feedback: Dies und das..."
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

        log.info "Blog Domain Objects created!"
    }

    def destroy = { }
}