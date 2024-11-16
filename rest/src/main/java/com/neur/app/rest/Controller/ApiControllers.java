package com.neur.app.rest.Controller;

import com.neur.app.rest.Models.User;
import com.neur.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Hello World";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping(value = "/users")
    public String saveUser(@RequestBody User user) {
        userRepo.save(user);
        return "Saved!";
    }

    @PutMapping(value = "/user/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setRole(user.getRole());
        userRepo.save(updatedUser);
        return "Updated user!";
    }

    @DeleteMapping(value = "/user/{id}")
    public String deleteUser(@PathVariable long id) {
        userRepo.delete(userRepo.findById(id).get());
        return "User deleted";
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
