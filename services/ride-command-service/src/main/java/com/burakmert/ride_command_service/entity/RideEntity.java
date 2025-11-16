package com.burakmert.ride_command_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rides")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class RideEntity {

    @Id
    private String rideId;

    private String riderId;
    private String driverId;

    private String pickup;
    private String destination;

    @Enumerated(EnumType.STRING)
    private RideStatus status;

    private long createdAt;
    private long updatedAt;
}
