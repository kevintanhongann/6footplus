import com.sixfootplus.blog.*

class BootStrap {

    def init = { servletContext ->

        if (BlogUser.findByUsername('admin')) {
            log.info 'Database is initialized already!'
            return
        }

        BlogUser user = new BlogUser (username:'admin', password:'geheim',
            email:'grails@moscon.de')
        BlogUser anotherUser = new BlogUser()


        if ( !user.save() ) {
            log.error "SAVING OF USER FAILED:\n ${user.errors}"
            return
        }

        BlogArticle article = new BlogArticle()
        article.subject='Java Magazin bringt Grails Tutorial'
        article.body='''Es ist soweit:
In der neusten Ausgabe des Java Magazins findet sich der erste Teil eines Grails Tutorials.
Insgesamt wird es zwei weitere Teile geben.
Der aufmerksame Leser wird am Ende des Tutorial in der Lage sein, selber professionelle
Grails Applikationen zu schreiben!
    '''
        article.author=user

        article.save()
        if (article.hasErrors()) {
            log.error "SAVING OF ARTICLE FAILED:\n ${article.errors}"
            return
        }

        BlogComment comment = new BlogComment()
        comment.message = "Danke für den Artikel. Mein konstruktives Feedback: Dies und das..."
        comment.article = article
        if ( !comment.save() ) {
            log.error "SAVING OF COMMENT FAILED:\n ${comment.errors}"
            return
        }

        log.info "Blog Domain Objects created!"
    }

    def destroy = { }
} 