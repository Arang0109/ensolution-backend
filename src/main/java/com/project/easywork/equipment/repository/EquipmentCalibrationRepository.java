package com.project.easywork.equipment.repository;

import com.project.easywork.equipment.domain.persistance.EquipmentCalibration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentCalibrationRepository extends JpaRepository<EquipmentCalibration, Long> {
}
