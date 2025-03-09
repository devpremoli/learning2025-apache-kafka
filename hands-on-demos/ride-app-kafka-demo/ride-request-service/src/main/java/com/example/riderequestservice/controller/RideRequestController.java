package com.example.riderequestservice.controller;


import com.example.common.model.RideRequestDTO;
import com.example.riderequestservice.service.RideRequestService;
import com.example.riderequestservice.model.RideRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideRequestController {

    private final RideRequestService rideRequestService;

    public RideRequestController(RideRequestService rideRequestService) {
        this.rideRequestService = rideRequestService;
    }

    @PostMapping("/request")
    public ResponseEntity<RideRequest> requestRide(@RequestBody RideRequestDTO rideRequestDto) {
        RideRequest savedRide = rideRequestService.createRideRequest(rideRequestDto);
        return ResponseEntity.ok(savedRide);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<RideRequest>> getUserRides(@PathVariable String userId) {
        return ResponseEntity.ok(rideRequestService.getUserRideRequests(userId));
    }
}

