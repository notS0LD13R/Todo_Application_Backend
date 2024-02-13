package app.todo.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(private val jwtHandler: JwtHandler, private val jwtToUserPrincipal: JwtToUserPrincipal) :
    OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        try {
            val token = extractToken(request)
            if (!token.isNullOrBlank()) {
                val user = jwtHandler.verifyToken(token)
                user.let {
                    SecurityContextHolder.getContext().authentication =
                        UserAuthenticationToken(jwtToUserPrincipal.convert(user))

                }
            }
        } catch (e: Exception) {
            print("Error:${e}")
        }
        filterChain.doFilter(request, response)
    }

    fun extractToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7)
        }
        return null
    }
}