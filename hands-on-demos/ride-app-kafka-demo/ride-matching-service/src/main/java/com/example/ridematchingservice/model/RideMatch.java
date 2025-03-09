package com.example.ridematchingservice.model;

import com.example.common.model.RideStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ride_matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "ride_matches_seq",
        sequenceName = "ride_matches_seq",
        allocationSize = 1  // Ensure consistency with PostgreSQL sequence
)
public class RideMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ride_matches_seq")
    private Long id;

    private String userId;
    private String driverId;
    private String pickupLocation;
    private String dropoffLocation;

    @Enumerated(EnumType.STRING)
    private RideStatus status = RideStatus.MATCHED;

    private LocalDateTime timestamp = LocalDateTime.now();
}
