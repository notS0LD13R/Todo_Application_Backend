package app.todo.todo

import org.springframework.stereotype.Service
import kotlin.jvm.optionals.toList

@Service
class TodoService(val db: TodoRepository) {
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
        println(result)
    }
}