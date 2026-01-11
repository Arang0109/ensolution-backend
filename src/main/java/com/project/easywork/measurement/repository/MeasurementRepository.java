package com.project.easywork.measurement.repository;

import com.project.easywork.measurement.dto.document.MeasurementDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeasurementRepository extends MongoRepository<MeasurementDoc, String> {
  Optional<MeasurementDoc> findByPlanId(Long planId);
  void deleteByPlanId(Long planId);
}