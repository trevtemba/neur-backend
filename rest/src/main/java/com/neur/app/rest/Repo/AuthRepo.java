package com.neur.app.rest.Repo;

import com.neur.app.rest.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<Users, Long> {
}
