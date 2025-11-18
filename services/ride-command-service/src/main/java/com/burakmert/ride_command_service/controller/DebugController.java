package com.burakmert.ride_command_service.controller;

import com.burakmert.ride_command_service.entity.RideEntity;
import com.burakmert.ride_command_service.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/debug")
public class DebugController {

    private final RideRepository repo;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Health check success.");
    }

    @GetMapping("/rides")
    public ResponseEntity<List<RideEntity>> getAll() {
        return ResponseEntity.ok(repo.findAll());
    }

}
