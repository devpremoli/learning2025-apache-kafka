package com.example.riderequestservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.example.common.model.RideStatus;

@Entity
@Table(name = "ride_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "ride_requests_seq",
        sequenceName = "ride_requests_seq",
        allocationSize = 1  // Ensure consistency with PostgreSQL sequence
)
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ride_requests_seq")
    private Long id;

    private String userId;
    private String pickupLocation;
    private String dropoffLocation;

    @Enumerated(EnumType.STRING)
    private RideStatus status = RideStatus.REQUESTED;

    private LocalDateTime timestamp = LocalDateTime.now();
}
