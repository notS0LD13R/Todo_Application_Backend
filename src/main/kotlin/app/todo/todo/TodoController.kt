package app.todo.todo

import org.springframework.web.bind.annotation.*

@RestController
class TodoController(val service: TodoService) {
    @GetMapping("/")
    fun index(): List<Todo> {
        return service.getTodos()
    }

    @GetMapping("/{id}")
    fun index(@PathVariable id: String): Todo {
        return service.getTodoById(id)
    }

    @PostMapping("/")
    fun post(@RequestBody message: List<Todo>) {
        for (todo in message) {
            service.addTodo(todo)
        }
    }

    @PostMapping("/kafka")
    fun producer(@RequestBody message: Todo):TodoResponse{
        return service.produceTodo(message)
    }
}