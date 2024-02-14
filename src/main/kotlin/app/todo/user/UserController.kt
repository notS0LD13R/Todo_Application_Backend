package app.todo.user

import app.todo.exception.ResponseException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val service: UserService) {

    @GetMapping("/user")
    fun getAllUsers(): List<User> {
        try {
            return service.getUsers()
        } catch (e: Exception) {
            throw ResponseException(e.message ?: "User fetch failed", e)
        }
    }

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable id: String): User {
        try {
            return service.getUser(id)
        } catch (e: Exception) {
            throw ResponseException(e.message ?: "User fetch failed", e)
        }
    }
}
