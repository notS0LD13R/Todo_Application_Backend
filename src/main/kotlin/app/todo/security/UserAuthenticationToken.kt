package app.todo.security

import org.springframework.security.authentication.AbstractAuthenticationToken

class UserAuthenticationToken(private val principal: UserPrincipal) :
    AbstractAuthenticationToken(principal.authorities) {

        init{
            isAuthenticated = true
        }
    override fun getCredentials() = null

    override fun getPrincipal() = principal


}