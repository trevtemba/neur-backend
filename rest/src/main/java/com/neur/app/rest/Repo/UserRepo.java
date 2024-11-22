package com.neur.app.rest.Repo;

import com.neur.app.rest.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
}
