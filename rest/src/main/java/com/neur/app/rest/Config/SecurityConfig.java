package com.neur.app.rest.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        //We are going to make the http stateless, so disable this. (lambda notation:)
        //Using builder design pattern to configure the http object
        http
                .csrf(AbstractHttpConfigurer::disable)
        //Any requests can only be made if the request is authenticated
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
        //Enabled the spring security login GUI with logic
                .formLogin(Customizer.withDefaults())
        //Allows postman access
                .httpBasic(Customizer.withDefaults())
        //Makes http session stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //returns object of "securityFilterChain"
        return http.build();
    }

    // This is what allows us to set the valid credentials
    // now the api can only be used by the users within these details
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("trevor")
//                .password("universe")
//                .roles("Admin")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("carter")
//                .password("lego")
//                .roles("User")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
