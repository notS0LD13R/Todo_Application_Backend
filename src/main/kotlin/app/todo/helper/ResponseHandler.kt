package app.todo.helper

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component


data class ResponseBody(val error: Boolean, val message: String, val response: Any?)
@Component
class ResponseHandler(private val jsonHandler: JsonHandler) {
    fun create(body: Any?, message: String, error:Boolean=false, status: Int=200): ResponseEntity<String> {
        return ResponseEntity.status(status).header("Content-Type","application/json").body(
                jsonHandler.stringify(ResponseBody(error, message, body))
            )
    }
}