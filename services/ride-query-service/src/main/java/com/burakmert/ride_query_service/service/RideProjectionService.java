package com.burakmert.ride_query_service.service;

import com.burakmert.ride_query_service.model.RideStatus;
import com.burakmert.ride_query_service.model.RideView;
import com.burakmert.ride_query_service.repository.RideViewRepository;
import com.burakmert.rides.avro.RideAssignment;
import com.burakmert.rides.avro.RideCompletion;
import com.burakmert.rides.avro.RideRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RideProjectionService {

    private final RideViewRepository repository;

    public void apply(RideRequest event) {
        if (repository.existsById(event.getRideId().toString())) {
            return;
        }

        RideView view = RideView.builder()
                .rideId(event.getRideId().toString())
                .riderId(event.getRiderId().toString())
                .pickup(event.getPickup().toString())
                .destination(event.getDestination().toString())
                .status(RideStatus.REQUESTED)
                .createdAt(event.getTimestamp())
                .updatedAt(event.getTimestamp())
                .build();

        repository.save(view);
    }

    public void apply(RideAssignment event) {
        RideView view = repository.findById(event.getRideId().toString())
                .orElseGet(() -> RideView.builder()
                        .rideId(event.getRideId().toString())
                        .riderId(event.getRiderId().toString())
                        .pickup(event.getPickup().toString())
                        .destination(event.getDestination().toString())
                        .status(RideStatus.REQUESTED)
                        .createdAt(event.getTimestamp())
                        .build());

        view.setDriverId(event.getDriverId().toString());
        view.setStatus(RideStatus.ASSIGNED);
        view.setUpdatedAt(Instant.now().toEpochMilli());

        repository.save(view);
    }

    public void apply(RideCompletion event) {
        repository.findById(event.getRideId().toString()).ifPresent(view -> {
            view.setStatus(RideStatus.COMPLETED);
            view.setUpdatedAt(event.getTimestamp());
            repository.save(view);
        });
    }

}
