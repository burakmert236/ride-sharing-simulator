package com.burakmert.rider_service.publisher;

import com.burakmert.rides.avro.RideRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RideEventPublisher {
    private final KafkaTemplate<String, RideRequest> kafkaTemplate;

    @Value("${topics.ride-requests}")
    private String rideRequestsTopic;

    public RideEventPublisher(KafkaTemplate<String, RideRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishRideRequest(String riderId, String pickup, String destination) {
        RideRequest event = RideRequest.newBuilder()
                .setRideId(UUID.randomUUID().toString())
                .setRiderId(riderId)
                .setPickup(pickup)
                .setDestination(destination)
                .setTimestamp(Instant.now().toEpochMilli())
                .build();

        kafkaTemplate.send(rideRequestsTopic, event.getRideId().toString(), event);
    }
}
