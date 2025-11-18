package com.burakmert.ride_query_service.listener;

import com.burakmert.ride_query_service.service.RideProjectionService;
import com.burakmert.rides.avro.RideRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RideRequestListener {

    private final RideProjectionService projectionService;

    @KafkaListener(topics = "${topics.ride-requests}")
    public void consume(RideRequest event) {
        projectionService.apply(event);
    }

}
