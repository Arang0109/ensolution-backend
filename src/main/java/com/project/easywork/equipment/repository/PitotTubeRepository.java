package com.project.easywork.equipment.repository;

import com.project.easywork.equipment.domain.persistance.PitotTube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitotTubeRepository extends JpaRepository<PitotTube, Long> {
}
