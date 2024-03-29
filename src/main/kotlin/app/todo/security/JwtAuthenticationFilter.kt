package app.todo.security

import app.todo.helper.JsonHandler
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

    private val socketURI = "/socket"
    private val exemptedPattern = arrayOf("(/auth/)([\\w/]+)")

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
//        try {
            if (isExemptedURL(request)) {
                filterChain.doFilter(request, response)
                return
            }
            val token = extractToken(request)
            if (!token.isNullOrBlank()) {
                val jwt = jwtHandler.verifyToken(token)
                jwt.let {
                    SecurityContextHolder.getContext().authentication =
                        UserAuthenticationToken(jwtToUserPrincipal.convert(jwt))
                }
            }
            filterChain.doFilter(request, response)
//        } catch (e: TokenExpiredException) {
//            response.addHeader("Content-Type", "application/json")
//            response.status = HttpStatus.UNAUTHORIZED.value()
//            response.writer.write(
//                jsonHandler.stringify(ResponseBody(true, "Token expired", e.javaClass))
//            )
//            response.flushBuffer()
//        } catch (e: Exception) {
//            print(e)
//            response.addHeader("Content-Type", "application/json")
//            response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
//            response.writer.write(
//                jsonHandler.stringify(
//                    ResponseBody(true, e.message ?: "Something went wrong", e.javaClass)
//                )
//            )
//            response.flushBuffer()
//        }
    }

    fun isExemptedURL(request: HttpServletRequest): Boolean {
        val uri = request.requestURI
        return exemptedPattern.any { Regex(it).matches(uri) }
    }

    fun extractToken(request: HttpServletRequest): String? {
        try {
            val token = request.getHeader("Authorization")
            if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
                return token.substring(7)
            }
        } catch (e: Exception) {
            throw Exception("Error extracting token")
        }
        return null
    }
}
