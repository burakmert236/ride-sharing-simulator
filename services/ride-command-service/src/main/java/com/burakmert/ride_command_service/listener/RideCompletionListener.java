package com.burakmert.ride_command_service.listener;

import com.burakmert.ride_command_service.service.RideAssignmentService;
import com.burakmert.rides.avro.RideCompletion;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RideCompletionListener {

    private final RideAssignmentService rideAssignmentService;

    @KafkaListener(topics = "${topics.ride-completions}")
    public void listen(RideCompletion event) {
        rideAssignmentService.completeRide(event.getRideId().toString());
    }

}
