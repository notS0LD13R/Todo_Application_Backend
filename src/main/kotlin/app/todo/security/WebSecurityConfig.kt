package app.todo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun applicationSecurity(http:HttpSecurity):SecurityFilterChain{

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