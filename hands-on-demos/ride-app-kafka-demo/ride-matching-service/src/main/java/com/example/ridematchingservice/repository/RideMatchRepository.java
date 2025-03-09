package com.example.ridematchingservice.repository;

import com.example.ridematchingservice.model.RideMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideMatchRepository extends JpaRepository<RideMatch, Long> {
    List<RideMatch> findByUserId(String userId);
}
