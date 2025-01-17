package com.neur.app.rest.Controller;
import ch.qos.logback.classic.Logger;
import com.neur.app.rest.Models.DeleteServiceRequestDTO;
import com.neur.app.rest.Models.Services;
import com.neur.app.rest.Models.Users;
import com.neur.app.rest.Models.BioUpdateDTO;
import com.neur.app.rest.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PatchMapping(value = "/users/{id}/about")
    public ResponseEntity<?> updateUserBio(@PathVariable long id, @RequestBody BioUpdateDTO bioUpdateDTO) {
       return userService.updateUserBio(id, bioUpdateDTO);
    }

    @DeleteMapping(value = "/users/{id}")
    public String deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }

    @PostMapping(value = "/users/{id}/services")
    public ResponseEntity<?> createService(@PathVariable long id, @RequestBody Services service) {
        return userService.createService(id, service);
    }

    @GetMapping(value = "/users/{id}/services")
    public ResponseEntity<?> getServices(@PathVariable long id) {
        return userService.getServices(id);
    }

    @PatchMapping(value = "/users/{id}/services")
    public ResponseEntity<?> updateService(@PathVariable long id, @RequestBody Services service) {
        return userService.updateService(id, service);
    }

    @DeleteMapping(value = "/users/{id}/services")
    public ResponseEntity<?> deleteService(@PathVariable long id, @RequestBody DeleteServiceRequestDTO service) {
        return userService.deleteService(id, service);
    }

    @PostMapping(value = "/users/{id}/client-images",
            consumes= MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadClientImg(@PathVariable long id, @RequestParam("file") MultipartFile imageFile) throws IOException {
        return userService.uploadClientImg(id, imageFile);
    }

    @DeleteMapping(value = "/users/{id}/client-images")
    public ResponseEntity<?> deleteClientImg(@PathVariable long id, @RequestBody long imageId) {
        return userService.deleteClientImg(id, imageId);
    }

    @GetMapping(value = "/users/{id}/client-images")
    public ResponseEntity<?> getClientImgs(@PathVariable long id) {
        return userService.getClientImgs(id);
    }
}
