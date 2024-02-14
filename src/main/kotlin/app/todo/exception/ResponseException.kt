package app.todo.exception

class ResponseException(private val msg:String,private val root:Exception,private val status:Int=500):Exception(msg) {
    fun getRootException(): Exception {
        return root
    }

    fun getStatus():Int{
        return status
    }
}