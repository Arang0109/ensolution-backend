package com.project.easywork.equipment.repository;

import com.project.easywork.equipment.domain.EquipStatus;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EquipmentRepository extends MongoRepository<EquipmentDoc, String> {
  List<EquipmentDoc> findByStatusNot(EquipStatus status);
  List<EquipmentDoc> findByTypeAndStatusNot(
      EquipType type,
      EquipStatus status
  );
}