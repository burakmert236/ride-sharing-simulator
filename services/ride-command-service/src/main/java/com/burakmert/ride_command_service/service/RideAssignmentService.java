package com.burakmert.ride_command_service.service;

import com.burakmert.ride_command_service.entity.RideEntity;
import com.burakmert.ride_command_service.entity.RideStatus;
import com.burakmert.ride_command_service.repository.RideRepository;
import com.burakmert.rides.avro.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RideAssignmentService {

    private final RideRepository rideRepository;

    public RideEntity saveRequestedRide(RideRequest event) {
        RideEntity entity = RideEntity.builder()
                .rideId(event.getRideId().toString())
                .riderId(event.getRiderId().toString())
                .pickup(event.getPickup().toString())
                .destination(event.getDestination().toString())
                .status(RideStatus.REQUESTED)
                .createdAt(event.getTimestamp())
                .updatedAt(event.getTimestamp())
                .build();

        return rideRepository.save(entity);
    }

    public RideAssignment assignDriver(RideEntity ride) {

        // TODO: Replace later with real driver service logic
        String driverId = "driver-" + UUID.randomUUID().toString().substring(0, 5);

        ride.setDriverId(driverId);
        ride.setStatus(RideStatus.ASSIGNED);
        ride.setUpdatedAt(Instant.now().toEpochMilli());

        rideRepository.save(ride);

        return RideAssignment.newBuilder()
                .setRideId(ride.getRideId())
                .setRiderId(ride.getRiderId())
                .setDriverId(driverId)
                .setPickup(ride.getPickup())
                .setDestination(ride.getDestination())
                .setTimestamp(Instant.now().toEpochMilli())
                .build();
    }
}
