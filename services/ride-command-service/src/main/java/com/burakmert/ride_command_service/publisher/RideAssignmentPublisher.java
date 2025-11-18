package com.burakmert.ride_command_service.publisher;

import com.burakmert.rides.avro.RideAssignment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RideAssignmentPublisher {
    @Value("${topics.ride-assignments}")
    private String topic;

    private final KafkaTemplate<String, Object> template;

    public void publish(RideAssignment event) {
        template.send(topic, event.getRideId().toString(), event);
    }
}
