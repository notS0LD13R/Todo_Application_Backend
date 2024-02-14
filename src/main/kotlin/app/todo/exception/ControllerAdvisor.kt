package app.todo.exception

import app.todo.helper.ResponseHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice class ControllerAdvisor(
    private val responseHandler: ResponseHandler
) : ResponseEntityExceptionHandler() {

    @ExceptionHandler
    fun handleResponseHandler(error:ResponseException):ResponseEntity<String>{
        return responseHandler.create(error.getRootException().javaClass,error.message?:"Something went wrong",true,error.getStatus())
    }
}
