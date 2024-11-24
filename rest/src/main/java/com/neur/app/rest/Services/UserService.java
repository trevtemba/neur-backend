package com.neur.app.rest.Services;

import com.neur.app.rest.Models.Users;
import com.neur.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authManager;

    private final Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(
            16, 32, 1, 65536, 3);

    public List<Users> getUsers() {
        return userRepo.findAll();
    }
    public String registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "User successfully saved";
    }

    public String verifyLogin(Users user) {

//        Users dbUser = userRepo.findByUsername(user.getUsername());
//
//        if (dbUser == null) {
//            return "Incorrect username/password";
//        }
//
//        if (!(encoder.matches(user.getPassword(), dbUser.getPassword()))) {
//            return "Incorrect username/password";
//        }
//
//        return "Valid credentials, retrieving token!";

        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return "Valid credentials, retrieving token!";
        }

        return "Incorrect username/password";
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
