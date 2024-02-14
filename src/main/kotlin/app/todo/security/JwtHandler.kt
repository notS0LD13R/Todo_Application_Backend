package app.todo.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class JwtHandler {

    @Value("\${jwt.secret}")
    private lateinit var secretKey: String
    private val expireDuration:Long = 1 //In hours
    fun generateToken(userId: String, username: String, roles: List<String>): String {
        return JWT.create().withSubject(userId).withClaim("roles", roles).withClaim("username",username)
            .withExpiresAt(Instant.now().plus(Duration.of(expireDuration, ChronoUnit.HOURS))).sign(Algorithm.HMAC256(secretKey))
    }

    fun verifyToken(token: String): DecodedJWT {
        return JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token)
    }



}