package com.neur.app.rest.Config;

import com.neur.app.rest.Config.Filters.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        //We are going to make the http stateless, so disable this. (lambda notation:)
        //Using builder design pattern to configure the http object
        http
                .csrf(AbstractHttpConfigurer::disable)
        //Any requests can only be made if the request is authenticated (except register and login requests)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("users/register", "users/login")
                        .permitAll()
                        .anyRequest().authenticated())
        //Enabled the spring security login GUI with logic
                .formLogin(Customizer.withDefaults())
        //Allows postman access
                .httpBasic(Customizer.withDefaults())
        //Makes http session stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        //returns object of "securityFilterChain"
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();

    }

    // We want to make our own authentication provider...
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // We choose to use the Dao Authentication provider, which needs...
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // A password encoder, was bcrypt, switched to argon2...
        provider.setPasswordEncoder(new Argon2PasswordEncoder(
                16, 32, 1, 65536, 3));
        // And userDetailsService, this is an interface, so we implement it in services->MyUserDetailsService
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


}
