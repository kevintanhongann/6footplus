/**
 *
 * @author andisa
 */
abstract class AbstractWebTesting extends grails.util.WebTest {

    abstract test01Login()

    def loginSubmit() {

        group(description: 'Submit login'){
            setInputField(name:"username", value:"admin")
            setInputField(name:"password", value:"geheim")
            clickButton(label:"login")
            verifyText 'Logged in as: admin'
        }
    }
}

