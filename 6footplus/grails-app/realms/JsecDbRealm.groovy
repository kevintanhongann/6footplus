import org.jsecurity.authc.AccountException
import org.jsecurity.authc.IncorrectCredentialsException
import org.jsecurity.authc.UnknownAccountException
import org.jsecurity.authc.SimpleAccount

import com.sixfootplus.blog.*

class JsecDbRealm {
    
    static authTokenClass = org.jsecurity.authc.UsernamePasswordToken

    def credentialMatcher

    def authenticate(authToken) {
    
        log.info "Attempting to authenticate ${authToken.username} in DB realm..."
        def username = authToken.username

        // Null username is invalid
        if (username == null) {
            throw new AccountException('Null usernames are not allowed by this realm.')
        }

        // Get the user with the given username. If the user is not
        // found, then they don't have an account and we throw an
        // exception.
        def user = BlogUser.findByUsername(username)
        if (!user) {
            throw new UnknownAccountException("No account found for user [${username}]")
        }
        
        log.info "Found user '${user.username}' in DB"

        // Now check the user's password against the hashed value stored
        // in the database.
        def account = new SimpleAccount(username, user.password, "JsecDbRealm")
        if (!credentialMatcher.doCredentialsMatch(authToken, account)) {
            log.info 'Invalid password (DB realm)'
            throw new IncorrectCredentialsException("Invalid password for user '${username}'")
        }

        return account
    }

    def hasRole(principal, roleName) {
        def user = BlogUser.find("from ${BlogUser.name} as u left join fetch u.roles where u.username=?",[principal])
        return user.roles*.name.contains(roleName)
    }

    def hasAllRoles(principal, roles) {
        for (r in roles ) {
             if (!hasRole(principal,r)) return false
        }
        return true
    }

    def findConstructor(className) {
        // Load the required permission class.
        def clazz = this.class.classLoader.loadClass(className)

        // Check the available constructors. If any take two
        // string parameters, we use that one and pass in the
        // target and actions string. Otherwise we try a single
        // parameter constructor and pass in just the target.
        def preferredConstructor = null
        def fallbackConstructor = null
        clazz.declaredConstructors.each { constructor ->
            def numParams = constructor.parameterTypes.size()
            if (numParams == 2) {
                if (constructor.parameterTypes[0].equals(String) &&
                        constructor.parameterTypes[1].equals(String)) {
                    preferredConstructor = constructor
                }
            }
            else if (numParams == 1) {
                if (constructor.parameterTypes[0].equals(String)) {
                    fallbackConstructor = constructor
                }
            }
        }

        return (preferredConstructor != null ? preferredConstructor : fallbackConstructor)
    }
}
