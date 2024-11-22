package com.neur.app.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        //We are going to make the http stateless, so disable this. (lambda notation)
        http.csrf(customizer -> customizer.disable());
        //Any requests can only be made if the request is authenticated
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        //Enabled the spring security login GUI with logic
        //http.formLogin(Customizer.withDefaults());
        //Allows postman access
        http.httpBasic(Customizer.withDefaults());
        //Note: no login page atm

        //Makes http session stateless
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        //returns object of "securityFilterChain"
        return http.build();
    }
}
