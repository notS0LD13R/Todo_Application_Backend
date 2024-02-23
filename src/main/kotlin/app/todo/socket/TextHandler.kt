package app.todo.socket

import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

 @Component
class TextHandler() : TextWebSocketHandler() {

    //    @Autowired
    //    val jsonHandler: JsonHandler
    private val sessionList = HashMap<WebSocketSession, String>()

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionList.remove(session)
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessionList[session] = session.toString()
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        session.sendMessage(TextMessage("Received the message ${message.payload}"))
    }

     fun broadcast(message:String){
         sessionList.keys.forEach{
             it.sendMessage(TextMessage(message))
         }
     }

}
