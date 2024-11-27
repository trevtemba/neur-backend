package com.neur.app.rest.Controller;
import com.neur.app.rest.Models.Users;
import com.neur.app.rest.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private UserService service;

    // root
    @GetMapping(value = "/")
    public String getPage(HttpServletRequest request) {
        return "Welcome to neur rest api " + request.getSession().getId();
    }

    // users
    @GetMapping(value = "/users")
    public List<Users> getUsers() {
        return service.getUsers();
    }

    @PostMapping(value = "/users/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) { return service.registerUser(user); }

    @PostMapping(value = "/users/login")
    public ResponseEntity<?> verifyLogin(@RequestBody Users user) { return service.verifyLogin(user); }

    @PostMapping(value = "/users/{id}/logout")
    public String logoutUser(@PathVariable long id) {
        return service.logoutUser(id);
    }
    // users/{id}
    @PutMapping(value = "/users/{id}")
    public String updateUser(@PathVariable long id, @RequestBody Users user) {
       return service.updateUser(id, user);
    }

    @DeleteMapping(value = "/users/{id}")
    public String deleteUser(@PathVariable long id) {
        return service.deleteUser(id);
    }
//    @PostMapping(value = "/auth/signup")
//    public String createAccount() {
//        return "Hello World";
//    }
//
//    @PostMapping(value = "/auth/login")
//    public String loginAccount() {
//        return "Hello World";
//    }
//
//    @PostMapping(value = "/auth/logout")
//    public String logoutAccount() {
//        return "Hello World";
//    }
//
//    @PostMapping(value = "/auth/forgot-password")
//    public String forgotPassword() {
//        return "Hello World";
//    }
//
//    @PostMapping(value = "/auth/reset-password")
//    public String resetPassword() {
//        return "Hello World";
//    }
//
//    @PostMapping(value = "/auth/reset-password")
//    public String refreshToken() {
//        return "Hello World";
//    }
//
//    @PostMapping(value = "/auth/verify-email")
//    public String verifyEmail() {
//        return "Hello World";
//    }
}
