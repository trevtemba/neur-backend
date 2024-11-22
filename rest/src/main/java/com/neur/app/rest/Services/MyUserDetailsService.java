package com.neur.app.rest.Services;

import com.neur.app.rest.Repo.UserRepo;
import com.neur.app.rest.Models.Users;
import com.neur.app.rest.Models.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// This service retrieves the user from the data, via their username.
@Service
public class MyUserDetailsService implements UserDetailsService {

    // Inject userRepo which implements JPA repo to communicate with database
    @Autowired
    private UserRepo userRepo;

    //Create our implementation of the loadUserByUsername method within UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepo.findByUsername(username);

        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not found");
        }

        // We want to return UserDetails, but it's an interface, so we create a class called UserPrinciple
        // to implement its methods so we can return an object that implements UserDetails.

        return new UserPrinciple(user);
    }
}
