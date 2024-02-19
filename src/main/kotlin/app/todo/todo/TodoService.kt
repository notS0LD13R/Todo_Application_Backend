package app.todo.todo

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.toList

@Service
class TodoService(private val db: TodoRepository,private val producer:KafkaTemplate<String,Todo>) {

    private val topicName = "chat_messages"
    fun getTodos(): List<Todo> {
        val result = db.findAll().toList()
        return result
    }

    fun getTodoById(id: String): Todo {
        val result = db.findById(id).toList()
        return result.first()
    }

    fun addTodo(todo: Todo) {
        val result = db.save(todo)
    }

    fun produceTodo(todo:Todo):TodoResponse{
//todo.id=todo.id?:""
            producer.send(topicName,todo)
            return TodoResponse("event successfully streamed to ${topicName}}",false)
    }

}