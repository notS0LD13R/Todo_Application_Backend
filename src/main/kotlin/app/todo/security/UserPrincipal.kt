package app.todo.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(private val userId:String,private val username:String,private val authorities:MutableCollection<SimpleGrantedAuthority>) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities

    override fun getPassword(): String = ""
    override fun getUsername(): String =username

    override fun isAccountNonExpired()=true

    override fun isAccountNonLocked()=true

    override fun isCredentialsNonExpired()=true

    override fun isEnabled() =true
}