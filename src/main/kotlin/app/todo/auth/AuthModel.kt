package app.todo.auth

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

data class LoginRequest(val username:String,val password:String)

data class LoginResponse(val msg:String,val accessToken:String,val refreshToken:String)


@Table("users")
data class User(@Id var id:String?,val username:String,val password:String)
//interface UserRepository : CrudRepository<User,String>