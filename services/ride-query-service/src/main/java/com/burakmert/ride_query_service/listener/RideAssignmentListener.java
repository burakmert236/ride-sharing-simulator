package com.burakmert.ride_query_service.listener;

import com.burakmert.ride_query_service.service.RideProjectionService;
import com.burakmert.rides.avro.RideAssignment;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RideAssignmentListener {

    private final RideProjectionService projectionService;

    @KafkaListener(topics = "${topics.ride-assignments}")
    public void consume(RideAssignment event) {
        projectionService.apply(event);
    }

}
