package app.todo.helper

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class JsonHandler (private val jsoner:ObjectMapper){

    fun objectToJson(obj:Any):String{
        return jsoner.writeValueAsString(obj)
    }
}