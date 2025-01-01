package com.neur.app.rest.Controller;
import com.neur.app.rest.Models.Users;
import com.neur.app.rest.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ApiControllers {

    @Autowired
    private UserService userService;

    // root
    @GetMapping(value = "/")
    public String getPage(HttpServletRequest request) {
        return "Welcome to neur rest api " + request.getSession().getId();
    }

    // users
    @GetMapping(value = "/users")
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(value = "/users/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) { return userService.registerUser(user); }

    @PostMapping(value = "/users/login")
    public ResponseEntity<?> verifyLogin(@RequestBody Users user) { return userService.verifyLogin(user); }

    @PostMapping(value = "/users/verify")
    public ResponseEntity<?> verifyCode(@RequestHeader("Authorization") String authHeader, @RequestBody Map<String, String> payload) { return userService.verifyCode(authHeader, payload.get("verificationCode")); }

    @PostMapping(value = "/users/{id}/logout")
    public String logoutUser(@PathVariable long id) {
        return userService.logoutUser(id);
    }

    @GetMapping(value = "/auth/getUser")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authHeader) {
        return userService.getUser(authHeader);
    }
    // users/{id}
    @PutMapping(value = "/users/{id}")
    public String updateUser(@PathVariable long id, @RequestBody Users user) {
       return userService.updateUser(id, user);
    }

    @DeleteMapping(value = "/users/{id}")
    public String deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
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
