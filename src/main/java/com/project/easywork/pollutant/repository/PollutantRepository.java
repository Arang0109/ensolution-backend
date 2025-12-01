package com.project.easywork.pollutant.repository;

import com.project.easywork.pollutant.domain.persistance.Pollutant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollutantRepository extends JpaRepository<Pollutant, Long> {

}
