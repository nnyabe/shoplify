package com.shoplify.shoplify.api.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class WebSecurityConfig {


    private final JWTRequestFilter jwtRequestFilter;

    public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {this.jwtRequestFilter = jwtRequestFilter;}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable);
        http.addFilterBefore(jwtRequestFilter, AuthenticationFilter.class);
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(  "/api/v1/auth/verify","/api/v1/auth/login","/api/v1/auth/register",
                                "/api/v1/auth/forgot", "/api/v1/auth/reset", "/api/v1/error")
                        .permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()  // Allow all users to view products
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/products/**").hasRole("ADMIN")  // Only admins can update
//                        .requestMatchers(HttpMethod.POST, "/api/v1/products/**").hasRole("ADMIN")  // Only admins can create
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasRole("ADMIN")  // Only admins can delete
//                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                        .anyRequest().permitAll()
//                        .authenticated()
        );
        return http.build();
    }

}
