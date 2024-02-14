package app.todo.security

import app.todo.helper.JsonHandler
import app.todo.helper.ResponseBody
import com.auth0.jwt.exceptions.TokenExpiredException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtHandler: JwtHandler,
    private val jwtToUserPrincipal: JwtToUserPrincipal,
    private val jsonHandler: JsonHandler
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
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
            filterChain.doFilter(request, response)
        } catch (e: TokenExpiredException) {
            response.addHeader("Content-Type","application/json")
            response.writer.write(
                jsonHandler.objectToJson(ResponseBody(true, "Token expired", e.javaClass))
            )
            response.flushBuffer()
        }catch (e:Exception){
            response.addHeader("Content-Type","application/json")
            response.writer.write(
                jsonHandler.objectToJson(ResponseBody(true, "Something went wrong", e.javaClass))
            )
            response.flushBuffer()

        }
    }

    fun extractToken(request: HttpServletRequest): String? {
        val token = request.getHeader("Authorization")
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7)
        }
        return null
    }
}
