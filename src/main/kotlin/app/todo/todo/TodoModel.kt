package app.todo.todo

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository

@Table("todos")
data class Todo(@Id var id: String?, val value: String)
interface TodoRepository : CrudRepository<Todo, String>