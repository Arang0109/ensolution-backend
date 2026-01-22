package com.project.easywork.equipment.service;

import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.domain.dto.EquipmentCreateReqD;

import java.util.List;

public interface IEquipmentService {
  EquipType supportType();
  EquipmentDoc register(EquipmentCreateReqD dto);
}
