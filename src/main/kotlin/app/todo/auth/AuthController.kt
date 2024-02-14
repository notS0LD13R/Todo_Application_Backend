package app.todo.auth

import app.todo.exception.ResponseException
import app.todo.helper.ResponseHandler
import app.todo.security.JwtHandler
import app.todo.user.UserService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.relational.core.conversion.DbActionExecutionException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val jwtHandler: JwtHandler,
    private val service: UserService,
    private val responseHandler: ResponseHandler
) {
    @PostMapping("/auth/login")
    fun login(@RequestBody req: LoginRequest): ResponseEntity<String> {
        try {
        val user = service.validateUser(req.username,req.password)
        val accessToken = jwtHandler.generateToken(user.id!!, user.username, user.roles)
        return responseHandler.create(
            LoginResponse(accessToken, "refresh 1"),
            "Successfully Logged In"
        )
        }catch (e:EmptyResultDataAccessException){
            throw ResponseException("User does not exist or invalid credentials",e,400)
        }
    }

    @PostMapping("/auth/register")
    fun register(@RequestBody req: RegisterRequest): ResponseEntity<String> {
        try{

        val user = service.createUser(req.username, req.password)
        val accessToken = jwtHandler.generateToken(user.id!!, req.username, user.roles)
        return responseHandler.create(
            RegisterResponse(accessToken, "refresh 2"),
            "Successfully Registered"
        )
        }catch (e: DbActionExecutionException){
            throw ResponseException("User already exists",e,400)
        }
    }
}
