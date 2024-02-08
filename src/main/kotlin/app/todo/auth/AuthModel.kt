package app.todo.auth

data class LoginRequest(val username:String,val password:String)

data class LoginResponse(val msg:String,val accessToken:String,val refreshToken:String)