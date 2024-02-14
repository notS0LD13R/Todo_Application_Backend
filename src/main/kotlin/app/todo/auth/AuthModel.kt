package app.todo.auth


data class LoginRequest(val username:String,val password:String)
data class LoginResponse(val accessToken:String,val refreshToken:String)
data class RegisterRequest(val username:String,val password:String)
data class RegisterResponse(val accessToken:String,val refreshToken:String)

