package com.project.easywork.client.repository;

import com.project.easywork.client.domain.persistance.StackMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StackMeasurementRepository extends JpaRepository<StackMeasurement, Long> {
  List<StackMeasurement> findStackMeasurementsByStackId(Long stackId);
}
