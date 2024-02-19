package app.todo.socket

import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

//@Component
class TextHandler() : TextWebSocketHandler() {

//    @Autowired
//    val jsonHandler: JsonHandler
    private val sessionList = HashMap<WebSocketSession, User>()

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println(session)
        sessionList -= session
    }
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
//        val json = jsonHandler.parse(message.payload)
        println(message.payload)
    }
}
