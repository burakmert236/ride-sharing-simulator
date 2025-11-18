package com.burakmert.driver_service.listener;

import com.burakmert.driver_service.service.DriverService;
import com.burakmert.rides.avro.RideAssignment;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RideAssignmentListener {

    private final DriverService driverService;

    @KafkaListener(topics = "${topics.ride-assignments}")
    public void listenRideAssignment(RideAssignment event) {
        driverService.apply(event);
    }

}
