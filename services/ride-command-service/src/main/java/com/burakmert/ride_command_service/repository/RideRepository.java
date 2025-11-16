package com.burakmert.ride_command_service.repository;

import com.burakmert.ride_command_service.entity.RideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<RideEntity, String> {
}
