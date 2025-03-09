package com.example.ridematchingservice.service;

import com.example.common.model.RideRequestDTO;
import com.example.common.model.RideMatchDTO;
import com.example.ridematchingservice.model.RideMatch;
import com.example.ridematchingservice.repository.RideMatchRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RideMatchService {

    private static final String CONSUMER_TOPIC = "ride-requests";
    private static final String PRODUCER_TOPIC = "ride-matches";
    private static final String CONSUMER_GROUP = "ride-matching-group";

    private final RideMatchRepository rideMatchRepository;
    private final KafkaTemplate<String, RideMatchDTO> kafkaTemplate;

    public RideMatchService(RideMatchRepository rideMatchRepository, KafkaTemplate<String, RideMatchDTO> kafkaTemplate) {
        this.rideMatchRepository = rideMatchRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = CONSUMER_TOPIC, groupId = CONSUMER_GROUP)
    public void consumeRideRequest(RideRequestDTO rideRequestDTO) {
        System.out.println("Received Ride Request: " + rideRequestDTO.toString());

        // Simulate driver assignment
        String driverId = "DRIVER-" + UUID.randomUUID().toString().substring(0, 6);

        // Create a ride match
        RideMatch rideMatch = new RideMatch();
        rideMatch.setUserId(rideRequestDTO.getUserId());
        rideMatch.setPickupLocation(rideRequestDTO.getPickupLocation());
        rideMatch.setDropoffLocation(rideRequestDTO.getDropoffLocation());
        rideMatch.setDriverId(driverId);

        RideMatchDTO rideMatchDTO = new RideMatchDTO();
        rideMatchDTO.setUserId(rideRequestDTO.getUserId());
        rideMatchDTO.setPickupLocation(rideRequestDTO.getPickupLocation());
        rideMatchDTO.setDropoffLocation(rideRequestDTO.getDropoffLocation());
        rideMatchDTO.setDriverId(driverId);

        // Save to database
        rideMatchRepository.save(rideMatch);

        kafkaTemplate.send(PRODUCER_TOPIC, rideMatchDTO);
    }

    public List<RideMatch> getRideMatches(String userId) {
        return rideMatchRepository.findByUserId(userId);
    }
}
