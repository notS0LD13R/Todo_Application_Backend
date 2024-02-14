package app.todo.user

import app.todo.security.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.toList

@Service
class UserService(private val db: UserRepository, private val encoder: PasswordEncoder) {
    fun getUsers(): List<User> {
        return db.findAll().toList()
    }

    fun getUser(id: String): User {
        return db.findById(id).toList().first()
    }

    fun createUser(username: String, password: String): User {
        return db.save(User(null, username, encoder.encode(password), listOf()))
    }

    fun validateUser(username:String,password:String):User{
        try {

        return db.verify(username,password)
        }catch (e:Exception){
            print(e.toString())
            throw e
        }
    }
}
