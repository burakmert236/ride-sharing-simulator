package com.burakmert.ride_query_service.controller;

import com.burakmert.ride_query_service.model.RideStatus;
import com.burakmert.ride_query_service.model.RideView;
import com.burakmert.ride_query_service.repository.RideViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rides")
public class RideQueryController {

    private final RideViewRepository repository;

    @GetMapping
    public ResponseEntity<List<RideView>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<RideView> getById(@PathVariable String rideId) {
        return repository.findById(rideId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rider/{riderId}")
    public List<RideView> getByRider(@PathVariable String riderId) {
        return repository.findByRiderIdOrderByCreatedAtDesc(riderId);
    }

    @GetMapping("/driver/{driverId}/active")
    public List<RideView> getActiveByDriver(@PathVariable String driverId) {
        return repository.findByDriverIdAndStatus(driverId, RideStatus.ASSIGNED);
    }

    @GetMapping("/driver/{driverId}")
    public List<RideView> getAllByDriver(@PathVariable String driverId) {
        return repository.findByDriverId(driverId);
    }

}
