package com.project.easywork.equipment.repository;

import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.persistance.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
  List<Equipment> findByType(EquipType type);
}
