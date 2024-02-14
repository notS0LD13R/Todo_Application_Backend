package app.todo.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoder(private val encoder: BCryptPasswordEncoder) {

  fun encode(password: String): String {
    return encoder.encode(password)
  }

  fun isSame(raw: String, encoded: String): Boolean {
    return encoder.matches(raw, encoded)
  }
}
