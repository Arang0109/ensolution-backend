package com.project.easywork.equipment.repository;

import com.project.easywork.equipment.domain.persistance.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
