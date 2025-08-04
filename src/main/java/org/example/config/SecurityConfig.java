package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/persons/by-city").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("reader")
                .password("pass")
                .roles("READ")
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("writer")
                .password("pass")
                .roles("WRITE")
                .build();

        UserDetails user3 = User.withDefaultPasswordEncoder()
                .username("deleter")
                .password("pass")
                .roles("DELETE")
                .build();

        UserDetails user4 = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("pass")
                .roles("READ", "WRITE", "DELETE")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3, user4);
    }

}