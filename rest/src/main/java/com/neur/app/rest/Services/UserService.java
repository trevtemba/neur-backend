package com.neur.app.rest.Services;

import com.neur.app.rest.Models.*;
import com.neur.app.rest.Repo.ServiceRepo;
import com.neur.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private final Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(
            16, 32, 1, 65536, 3);

    public List<Users> getUsers() {
        return userRepo.findAll();
    }
    public ResponseEntity<?> registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("Vendor");
        user.setIsActive(true);
        user.setDateCreated(LocalDateTime.now());
        userRepo.save(user);

        ApiResponse response = new ApiResponse("User successfully registered!", "success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> verifyLogin(Users user) {

        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(new AuthResponse(jwtService.generateToken(user.getUsername()), "success", "123456"));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Incorrect username or password");
    }
    public ResponseEntity<?> verifyCode(String authHeader, String input) {

        if (Objects.equals(input, "123456")) {
            String token = authHeader.replace("Bearer ", "");
            UserDTO userDTO = new UserDTO(userRepo.findByUsername(jwtService.extractUserName(token)));
            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Incorrect verification code"));
    }

    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Users user = userRepo.findByUsername(jwtService.extractUserName(token));

        if (user != null) {
            UserDTO userDTO = new UserDTO(user);
            return ResponseEntity.ok(userDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
    public String updateUser(long id, Users user) {
        Users updatedUser = userRepo.findById(id).get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(encoder.encode(user.getPassword()));
        updatedUser.setEmail(user.getEmail());
        updatedUser.setRole(user.getRole());
        userRepo.save(updatedUser);
        return "Updated user!";
    }

    public ResponseEntity<?> updateUserBio(@PathVariable long id, @RequestBody BioUpdateDTO bioUpdateDTO) {
        Optional<Users> updatedUserOpt = userRepo.findById(id);

        if (updatedUserOpt.isPresent()) {
            Users updatedUser = updatedUserOpt.get();
            updatedUser.setBio(bioUpdateDTO.getBio());
            userRepo.save(updatedUser);

            return ResponseEntity.ok("Successfully updated " + updatedUser.getUsername() + "'s bio!");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    public String deleteUser(long id) {
        userRepo.delete(userRepo.findById(id).get());
        return "User deleted";
    }

    public String logoutUser(long id) {
        return "Successfully logged out!";
    }

    public ResponseEntity<?> createService(@PathVariable long id, @RequestBody Services service) {
        service.setBusinessUserId(id);
        Services createdService = serviceRepo.save(service);

        return ResponseEntity.ok(createdService);
    }

    public ResponseEntity<?> getServices(@PathVariable long id) {
        List<Services> services = serviceRepo.findByBusinessUserId(id);

        return ResponseEntity.ok(services);
    }

    public ResponseEntity<?> updateService(@PathVariable long id, @RequestBody Services service) {
        Optional<Services> existingServiceOpt = (serviceRepo.findByBusinessUserIdAndId(id, service.getId()));
        if (existingServiceOpt.isPresent()) {
            Services existingService = existingServiceOpt.get();

            existingService.setName(service.getName());
            existingService.setDuration(service.getDuration());
            existingService.setPrice(service.getPrice());
            existingService.setDescription(service.getDescription());

            Services updatedService = serviceRepo.save(existingService);
            return ResponseEntity.ok(updatedService);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Service " + service.getName() + " not found for user ID: " + id);
        }

    }

    public ResponseEntity<?> deleteService(@PathVariable long id, @RequestBody DeleteServiceRequestDTO service) {
        Optional<Services> existingServiceOpt = (serviceRepo.findByBusinessUserIdAndId(id, service.getServiceId()));
        if (existingServiceOpt.isPresent()) {
            Services existingService = existingServiceOpt.get();

            serviceRepo.delete(existingService);
            return ResponseEntity.ok(existingService.getName() + " service succesfully deleted!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Service " + service.getServiceId() + " not found for user ID: " + id);
        }

    }
}
