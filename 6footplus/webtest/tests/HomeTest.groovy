import org.springframework.web.context.request.RequestContextHolder 

class HomeTest extends AbstractWebTesting {

    def test01Login() {
        invoke      '/'
        verifyText  'Beitrag Nr.1'
    }

    def test02IndexCommentsPreviousAndShow() {

        group(description:'Verify home page'){
            invoke      '/'
            verifyText  'Beitrag Nr.1'
            verifyText  'Dies ist ein Testeintrag mit der Nr.1'
            verifyText  'comments (0)'
            verifyText  'Older articles'
            verifyText  'my recent tweets'
            verifyText  'my recent videos'
            verifyText  'my recent photos'
            verifyText  'my recent twitpics'
            verifyText  'more recent articles'
            verifyText  'more stuff'
            verifyText  'articles by tag'
        }
        
        group(description:'View and submit comment'){
            invoke      '/'
            clickLink   'comments (0)'
            setInputField(name: 'author', value: 'A name')
            setInputField(name: 'message', value: 'A comment')
            setInputField(name: 'captcha', value: '999')
            clickButton "submit your comment"
            //for some reason cannot read ajax response text, so have to reload page
            invoke      '/'
            verifyText  'comments (1)'
        }

        group(description: 'Paginate list of articles'){
            invoke      '/'
            clickLink   'Older articles'
            verifyText  'Beitrag Nr.5'
            verifyText  'Beitrag Nr.6'
            verifyText  'Beitrag Nr.7'
            clickLink   'Newer articles'
            verifyText  'Beitrag Nr.1'
        }

        group(description: 'Show article'){
            invoke      '/'
            clickLink   'Beitrag Nr.1'
            verifyText  'Beitrag Nr.1'
            clickLink   'comments (1)'
            setInputField(name: 'author', value: 'B name')
            setInputField(name: 'message', value: 'B comment')
            clickButton "submit your comment"
            invoke      '/'
            verifyText  'comments (2)'
        }
    }
}