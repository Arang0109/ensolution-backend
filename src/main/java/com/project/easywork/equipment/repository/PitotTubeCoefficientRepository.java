package com.project.easywork.equipment.repository;

import com.project.easywork.equipment.domain.persistance.PitotTubeCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitotTubeCoefficientRepository extends JpaRepository<PitotTubeCoefficient, Long> {
}
