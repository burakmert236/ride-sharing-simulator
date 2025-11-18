package com.burakmert.ride_command_service.listener;

import com.burakmert.ride_command_service.entity.RideEntity;
import com.burakmert.ride_command_service.publisher.RideAssignmentPublisher;
import com.burakmert.ride_command_service.service.RideAssignmentService;
import com.burakmert.rides.avro.RideRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RideRequestListener {

    private final RideAssignmentService assignmentService;
    private final RideAssignmentPublisher publisher;

    @KafkaListener(topics = "${topics.ride-requests}")
    public void consume(RideRequest event) {
        // 1. Save initial ride (REQUESTED)
        RideEntity ride = assignmentService.saveRequestedRide(event);

        // 2. Assign a driver
        var assignedEvent = assignmentService.assignDriver(ride);

        // 3. Publish RideAssignment
        publisher.publish(assignedEvent);
    }

}
