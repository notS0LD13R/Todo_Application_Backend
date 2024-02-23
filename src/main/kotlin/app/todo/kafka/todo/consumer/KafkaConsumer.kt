package app.todo.kafka.todo.consumer

import app.todo.socket.TextHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaConsumer(private val socket:TextHandler) {

    @Value("\${spring.kafka.topics.todo}")
    lateinit var topicName:String
    @KafkaListener(topics = ["\${spring.kafka.topics.todo}"], groupId = "1")
    fun listener(message:String){
        socket.broadcast(message)

    }
}