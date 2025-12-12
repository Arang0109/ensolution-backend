package com.project.easywork.schedule.repository;

import com.project.easywork.schedule.domain.persistance.SchedulePollutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulePollutantRepository extends JpaRepository<SchedulePollutant, Long> {
  List<SchedulePollutant> findSchedulePollutantsByScheduleId(Long scheduleId);
}