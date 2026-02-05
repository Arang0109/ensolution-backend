package com.project.easywork.equipment.repository;

import com.project.easywork.equipment.domain.document.EquipmentDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EquipmentRepository extends MongoRepository<EquipmentDoc, String> {
  @Query("{ 'spec._class': ?0 }")
  List<EquipmentDoc> findBySpecType(String specClass);
}
