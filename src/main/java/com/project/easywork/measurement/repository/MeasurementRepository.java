package com.project.easywork.measurement.repository;

import com.project.easywork.measurement.dto.document.MeasurementDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends MongoRepository<MeasurementDocument, String> {
}