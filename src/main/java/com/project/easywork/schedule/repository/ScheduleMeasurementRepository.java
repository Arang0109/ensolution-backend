package com.project.easywork.schedule.repository;

import com.project.easywork.schedule.domain.persistance.ScheduleMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleMeasurementRepository extends JpaRepository<ScheduleMeasurement, Long> {
  List<ScheduleMeasurement> findSchedulePollutantsByScheduleId(Long scheduleId);
}