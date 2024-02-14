package app.todo.user

import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository

@Table("users")
data class User(@Id var id:String?,val username:String,val password:String,val roles:List<String>)
interface UserRepository : CrudRepository<User, String> {

    @Query("SELECT * FROM users WHERE users.username = :username AND users.password = :password")
    fun verify(username: String, password: String):User


}