package com.example.ridematchingservice.controller;

import com.example.ridematchingservice.model.RideMatch;
import com.example.ridematchingservice.service.RideMatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class RideMatchController {

    private final RideMatchService rideMatchService;

    public RideMatchController(RideMatchService rideMatchService) {
        this.rideMatchService = rideMatchService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<RideMatch>> getUserRideMatches(@PathVariable String userId) {
        return ResponseEntity.ok(rideMatchService.getRideMatches(userId));
    }
}
