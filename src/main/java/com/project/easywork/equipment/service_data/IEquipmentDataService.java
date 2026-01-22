package com.project.easywork.equipment.service_data;

import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;

import java.util.List;

public interface IEquipmentDataService {
  EquipmentDoc save(EquipmentDoc doc);
  EquipmentDoc findById(String id);
  List<EquipmentDoc> findAll();
  List<EquipmentDoc> findByType(EquipType type);
}