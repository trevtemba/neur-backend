package com.neur.app.rest.Controller;

import com.neur.app.rest.Models.Users;
import com.neur.app.rest.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private UserService service;

    @GetMapping(value = "/")
    public String getPage() {
        return "Hello World";
    }

    @GetMapping(value = "/users")
    public List<Users> getUsers() {
        return service.getUsers();
    }

    @PostMapping(value = "/users")
    public String saveUser(@RequestBody Users user) {
        return service.saveUser(user);
    }

    @PutMapping(value = "/user/{id}")
    public String updateUser(@PathVariable long id, @RequestBody Users user) {
       return service.updateUser(id, user);
    }

    @DeleteMapping(value = "/user/{id}")
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
