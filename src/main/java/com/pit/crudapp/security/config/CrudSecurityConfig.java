package com.pit.crudapp.security.config;

import com.pit.crudapp.jwt.util.JwtAuthEntryPoint;
import com.pit.crudapp.jwt.util.JwtFilter;
import com.pit.crudapp.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class CrudSecurityConfig {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    JwtAuthEntryPoint authEntryPoint;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**") // ✅ allow H2 POST
                        .disable()

                )
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authEntryPoint))
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable) // ✅ allow iframe
                )
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/getToken", "/h2-console/**", "/user/add")
                                .permitAll()
                                .requestMatchers("/hospital/all", "/hospital/byId/**").hasAnyRole("user", "admin")
                                .requestMatchers("/hospital/add", "/hospital/update", "/hospital/delete/**").hasRole("admin")
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}
