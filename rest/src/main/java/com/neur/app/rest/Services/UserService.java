package com.neur.app.rest.Services;

import com.neur.app.rest.Models.Users;
import com.neur.app.rest.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<Users> getUsers() {
        return userRepo.findAll();
    }
    public String saveUser(Users user) {
        userRepo.save(user);
        return "User successfully saved";
    }
    public String updateUser(long id, Users user) {
        Users updatedUser = userRepo.findById(id).get();
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setRole(user.getRole());
        userRepo.save(updatedUser);
        return "Updated user!";
    }

    public String deleteUser(long id) {
        userRepo.delete(userRepo.findById(id).get());
        return "User deleted";
    }   

}
