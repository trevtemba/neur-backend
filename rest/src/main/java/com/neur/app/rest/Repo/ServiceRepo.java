package com.neur.app.rest.Repo;

import com.neur.app.rest.Models.Services;
import com.neur.app.rest.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepo extends JpaRepository<Services, Long> {

    List<Services> findByBusinessUserId(Long businessUserId);
}
