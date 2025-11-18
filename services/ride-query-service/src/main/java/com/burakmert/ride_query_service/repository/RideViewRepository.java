package com.burakmert.ride_query_service.repository;

import com.burakmert.ride_query_service.model.RideStatus;
import com.burakmert.ride_query_service.model.RideView;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RideViewRepository extends MongoRepository<RideView, String> {

    List<RideView> findByRiderIdOrderByCreatedAtDesc(String riderId);

    List<RideView> findByDriverIdAndStatus(String driverId, RideStatus status);

    List<RideView> findByDriverId(String driverId);
}
