package app.todo.todo

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository

@Table("todos")
data class Todo(@Id @JsonProperty("id") var id: String?, @JsonProperty("value") val value: String)
interface TodoRepository : CrudRepository<Todo, String>

data class TodoResponse(val msg:String,val error:Boolean)