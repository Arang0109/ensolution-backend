package com.project.easywork.schedule.service_data.impl;

import com.project.easywork.schedule.domain.persistance.ScheduleMeasurement;
import com.project.easywork.schedule.repository.ScheduleMeasurementRepository;
import com.project.easywork.schedule.service_data.IScheduleMeasurementDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleMeasurementDataService implements IScheduleMeasurementDataService {
  
  private final ScheduleMeasurementRepository scheduleMeasurementRepository;
  
  @Override
  public ScheduleMeasurement save(ScheduleMeasurement scheduleMeasurement) {
    return scheduleMeasurementRepository.save(scheduleMeasurement);
  }
  
  @Override
  public void saveAll(List<ScheduleMeasurement> scheduleMeasurements) {
    scheduleMeasurementRepository.saveAll(scheduleMeasurements);
  }
  
  @Override
  public List<ScheduleMeasurement> findAllByScheduleId(Long scheduleId) {
    return scheduleMeasurementRepository.findSchedulePollutantsByScheduleId(scheduleId);
  }
}
