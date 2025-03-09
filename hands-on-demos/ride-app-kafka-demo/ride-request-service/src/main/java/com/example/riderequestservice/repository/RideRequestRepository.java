package com.example.riderequestservice.repository;

import com.example.riderequestservice.model.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
    List<RideRequest> findByUserId(String userId);
}
