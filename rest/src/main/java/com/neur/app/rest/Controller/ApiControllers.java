package com.neur.app.rest.Controller;
import com.neur.app.rest.Models.Services;
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

    @PostMapping(value = "/users/{id}/services/create")
    public ResponseEntity<?> createService(@PathVariable long id, @RequestBody Services service) {
        return userService.createService(id, service);
    }

    @GetMapping(value = "/users/{id}/services")
    public ResponseEntity<?> getServices(@PathVariable long id) {
        return userService.getServices(id);
    }

}
