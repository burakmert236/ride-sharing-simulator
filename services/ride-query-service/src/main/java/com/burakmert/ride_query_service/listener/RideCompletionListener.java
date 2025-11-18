package com.burakmert.ride_query_service.listener;

import com.burakmert.ride_query_service.service.RideProjectionService;
import com.burakmert.rides.avro.RideCompletion;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RideCompletionListener {

    private final RideProjectionService projectionService;

    @KafkaListener(topics = "${topics.ride-completions}")
    public void consume(RideCompletion event) {
        projectionService.apply(event);
    }

}
