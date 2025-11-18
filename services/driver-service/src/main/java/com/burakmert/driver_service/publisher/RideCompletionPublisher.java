package com.burakmert.driver_service.publisher;

import com.burakmert.rides.avro.RideCompletion;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RideCompletionPublisher {

    @Value("${topics.ride-completions}")
    private String topic;

    private final KafkaTemplate<String, RideCompletion> kafkaTemplate;

    public void publish(RideCompletion event) {
        kafkaTemplate.send(topic, event.getRideId().toString(), event);
    }
}
