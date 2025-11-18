package com.burakmert.ride_query_service.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rides")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideView {

    @Id
    private String rideId;

    private String riderId;
    private String driverId;

    private String pickup;
    private String destination;

    private RideStatus status;

    @CreatedDate
    private long createdAt;
    private long updatedAt;
}
