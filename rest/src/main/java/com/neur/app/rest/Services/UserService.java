package com.neur.app.rest.Services;

import com.neur.app.rest.Models.Users;
import com.neur.app.rest.Models.ApiResponse;
import com.neur.app.rest.Models.AuthResponse;
import com.neur.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

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
        userRepo.save(user);

        ApiResponse response = new ApiResponse("User successfully registered!", "success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> verifyLogin(Users user) {

        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok(new AuthResponse(jwtService.generateToken(user.getUsername()), "success"));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Incorrect username or password");
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

    public String deleteUser(long id) {
        userRepo.delete(userRepo.findById(id).get());
        return "User deleted";
    }

    public String logoutUser(long id) {
        return "Successfully logged out!";
    }
}
