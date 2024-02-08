package app.todo.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class JwtHandler {

    @Value("\${jwt.secret}")
    private lateinit var secretKey:String

    fun generateToken(userId: String, roles: List<String>): String {

        return JWT
            .create()
            .withSubject(userId)
            .withClaim("roles",roles)
            .withExpiresAt(Instant.now().plus(Duration.of(1,ChronoUnit.DAYS)))
            .sign(Algorithm.HMAC256(secretKey))
    }
}