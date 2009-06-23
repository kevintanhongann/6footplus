class HomeTest extends grails.util.WebTest {

    def test01Index() {
        
        invoke      'http://localhost:8080'
        verifyText  'Beitrag Nr.1'
        verifyText  'Dies ist ein Testeintrag mit der Nr.1'
        verifyText  'comments (0)'
        verifyText  'Previous'
        verifyText  'my recent tweets'
        verifyText  'my recent videos'
        verifyText  'my recent photos'
        verifyText  'my recent twitpics'
        verifyText  'my contact details'
        verifyText  'my recent articles'
        verifyText  'more stuff'
    }

    def test02Comments() {

        invoke      'http://localhost:8080'

        clickLink   'comments (0)'

        setInputField(name: 'author', value: 'A name')
        setInputField(name: 'message', value: 'A comment')

        clickButton "submit your comment"

        //for some reason cannot read ajax response text, so have to reload page
        invoke      'http://localhost:8080'
        verifyText  'comments (1)'
    }

    def test03Previous() {

        invoke      'http://localhost:8080'

        clickLink   'Previous'
        verifyText  'Beitrag Nr.2'
    }

    def test04Show() {

        invoke      'http://localhost:8080'

        clickLink   'Beitrag Nr.1'
        verifyText  'Beitrag Nr.1'

        clickLink   'comments (1)'

        setInputField(name: 'author', value: 'B name')
        setInputField(name: 'message', value: 'B comment')

        clickButton "submit your comment"

        invoke      'http://localhost:8080'
        verifyText  'comments (2)'
    }
}