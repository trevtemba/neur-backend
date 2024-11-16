package com.neur.app.rest.Repo;

import com.neur.app.rest.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Long> {
}
