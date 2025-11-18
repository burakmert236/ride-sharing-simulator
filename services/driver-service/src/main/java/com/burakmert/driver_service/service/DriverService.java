package com.burakmert.driver_service.service;

import com.burakmert.driver_service.publisher.RideCompletionPublisher;
import com.burakmert.rides.avro.RideAssignment;
import com.burakmert.rides.avro.RideCompletion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final RideCompletionPublisher publisher;

    public void apply(RideAssignment event) {

        RideCompletion completionEvent = RideCompletion.newBuilder()
                .setRideId(event.getRideId())
                .setTimestamp(event.getTimestamp())
                .build();

        publisher.publish(completionEvent);
    }

}
