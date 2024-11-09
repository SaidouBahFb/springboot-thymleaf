package com.groupeisi.companyspringboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String LOGIN = "/login";
    private static final String SIGNUP = "/signup";
    private final UserDetailsService userDetailsService;
    private static final String ADMIN = "ADMIN";

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage(LOGIN))
//        Set permissions on endpoints
                .authorizeHttpRequests(auth -> auth
//            our public endpoints
                        .requestMatchers(HttpMethod.POST, SIGNUP).permitAll()
                        .requestMatchers(HttpMethod.GET, SIGNUP).permitAll()
                        .requestMatchers(HttpMethod.POST, LOGIN).permitAll()
                        .requestMatchers(HttpMethod.GET, LOGIN).permitAll()
                        .requestMatchers( "/css/**").permitAll()
//            our private endpoints
                        .requestMatchers(HttpMethod.GET, "/products/**").hasAnyRole(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/purshases/**").hasAnyRole(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/sales/**").hasAnyRole(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/welcome").hasAnyRole("USER", ADMIN)
                        .anyRequest().authenticated())
                .authenticationManager(authenticationManager)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
