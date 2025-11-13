package com.burakmert.rider_service.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.burakmert.rider_service.publisher.RideEventPublisher;

@RestController
@RequestMapping("/rides")
public class RideController {
    private final RideEventPublisher publisher;

    public RideController(RideEventPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Health check success");
    }

    @PostMapping("/request")
    public ResponseEntity<String> requestRide(@RequestBody RideRequestDto request) {
        publisher.publishRideRequest(request.riderId, request.pickup, request.destination);
        return ResponseEntity.ok("Ride requested successfully");
    }

    @Data
    private static class RideRequestDto {
        private String riderId;
        private String pickup;
        private String destination;
    }
}
