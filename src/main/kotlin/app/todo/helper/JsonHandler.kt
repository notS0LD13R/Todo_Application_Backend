package app.todo.helper

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class JsonHandler (private val jsoner:ObjectMapper){

    fun stringify(obj:Any):String{
        return jsoner.writeValueAsString(obj)
    }

    fun parse(json:String): JsonNode? {
        return jsoner.readTree(json)
    }
}