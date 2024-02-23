package app.todo.todo

import app.todo.exception.ResponseException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todo")
class TodoController(val service: TodoService) {
    @GetMapping("/")
    fun index(): List<Todo> {
        return service.getTodos()
    }

    @GetMapping("/", params = ["id"])
    fun index(@RequestParam id: String): Todo {
        return service.getTodoById(id)
    }

    @PostMapping("/")
    fun post(@RequestBody message: List<Todo>) {
        for (todo in message) {
            service.addTodo(todo)
        }
    }

    @PostMapping("/kafka")
    fun producer(@RequestBody message: Todo): TodoResponse {
        try {
            return service.produceTodo(message)
        } catch (e: Exception) {
            throw ResponseException(e.message ?: "Something went wrong", e)
        }
    }
}
