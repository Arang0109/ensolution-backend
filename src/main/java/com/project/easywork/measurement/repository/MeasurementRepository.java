package com.project.easywork.measurement.repository;

import com.project.easywork.measurement.dto.document.MeasurementDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeasurementRepository extends MongoRepository<MeasurementDocument, String> {
  Optional<MeasurementDocument> findByPlanId(Long planId);
  void deleteByPlanId(Long planId);
}