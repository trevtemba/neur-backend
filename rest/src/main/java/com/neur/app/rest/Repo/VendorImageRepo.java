package com.neur.app.rest.Repo;

import com.neur.app.rest.Models.VendorImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorImageRepo extends JpaRepository<VendorImages, Long> {

    List<VendorImages> findByBusinessUserId(Long businessUserId);
    Optional<VendorImages> findByBusinessUserIdAndId(Long id, Long businessUserId);
}
