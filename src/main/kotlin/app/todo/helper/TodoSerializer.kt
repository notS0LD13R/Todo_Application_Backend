package app.todo.helper

import app.todo.todo.Todo
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Serializer

class TodoSerializer : Serializer<Todo> {

    private val objectMapper = ObjectMapper()

    override fun serialize(topic: String?, data: Todo?): ByteArray {
        try {
            return objectMapper.writeValueAsBytes(data)
        } catch (e: Exception) {
            throw RuntimeException("Error serializing Todo object", e)
        }
    }
}
