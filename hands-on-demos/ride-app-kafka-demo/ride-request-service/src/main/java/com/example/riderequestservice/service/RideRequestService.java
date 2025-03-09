package com.example.riderequestservice.service;

import com.example.common.model.RideRequestDTO;
import com.example.riderequestservice.model.RideRequest;
import com.example.riderequestservice.repository.RideRequestRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideRequestService {

    private static final String TOPIC = "ride-requests";

    private final RideRequestRepository rideRequestRepository;
    private final KafkaTemplate<String, RideRequestDTO> kafkaTemplate;

    public RideRequestService(RideRequestRepository rideRequestRepository, KafkaTemplate<String, RideRequestDTO> kafkaTemplate) {
        this.rideRequestRepository = rideRequestRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public RideRequest createRideRequest(RideRequestDTO rideRequestDTO) {

        RideRequest rideRequest = new RideRequest();
        rideRequest.setUserId(rideRequestDTO.getUserId());
        rideRequest.setPickupLocation(rideRequestDTO.getPickupLocation());
        rideRequest.setDropoffLocation(rideRequestDTO.getDropoffLocation());

        kafkaTemplate.send(TOPIC, rideRequestDTO);

        return rideRequestRepository.save(rideRequest);
    }

    public List<RideRequest> getUserRideRequests(String userId) {
        return rideRequestRepository.findByUserId(userId);
    }
}

