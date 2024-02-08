package app.todo.auth

import app.todo.security.JwtHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val jwtHandler: JwtHandler
) {

    @Value("\${jwt.secret}")
    private lateinit var secretKey:String

    @PostMapping("/auth/login")
    fun login(@RequestBody req:LoginRequest):LoginResponse{
        val accessToken = jwtHandler.generateToken(
            req.username,
            listOf("user")
        )
        return LoginResponse("Success ${req.username} $secretKey",accessToken,"refresh 1")
    }
}