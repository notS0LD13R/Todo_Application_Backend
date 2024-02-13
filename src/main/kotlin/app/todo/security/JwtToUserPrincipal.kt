package app.todo.security

import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class JwtToUserPrincipal {

    fun convert(jwt: DecodedJWT): UserPrincipal {
        return UserPrincipal(jwt.subject, jwt.getClaim("username").toString(), extractAuthority(jwt))
    }

    private fun extractAuthority(jwt: DecodedJWT): MutableCollection<SimpleGrantedAuthority> {
        val roles = jwt.getClaim("roles")
        if (roles.isNull || roles.isMissing) return mutableListOf()
        return roles.asList(SimpleGrantedAuthority::class.java)

    }
}