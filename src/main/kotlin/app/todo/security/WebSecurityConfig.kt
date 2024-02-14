package app.todo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfig(private val jwtAuthenticationFilter:JwtAuthenticationFilter) {

    @Bean
    fun applicationSecurity(http:HttpSecurity):SecurityFilterChain{

        @Bean
        fun passwordEncoder(): BCryptPasswordEncoder {
            return BCryptPasswordEncoder();
        }

        http.addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        http
            .cors{it.disable()}
            .csrf{it.disable()}
            .sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
            .formLogin{it.disable()}
            .securityMatcher("/**")
            .authorizeHttpRequests{
                it
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            }


        return http.build()
    }
}