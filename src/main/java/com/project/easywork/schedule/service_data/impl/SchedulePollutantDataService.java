package com.project.easywork.schedule.service_data.impl;

import com.project.easywork.schedule.domain.persistance.SchedulePollutant;
import com.project.easywork.schedule.repository.SchedulePollutantRepository;
import com.project.easywork.schedule.service_data.ISchedulePollutantDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SchedulePollutantDataService implements ISchedulePollutantDataService {
  
  private final SchedulePollutantRepository schedulePollutantRepository;
  
  @Override
  public SchedulePollutant save(SchedulePollutant schedulePollutant) {
    return schedulePollutantRepository.save(schedulePollutant);
  }
  
  @Override
  public void saveAll(List<SchedulePollutant> schedulePollutants) {
    schedulePollutantRepository.saveAll(schedulePollutants);
  }
  
  @Override
  public List<SchedulePollutant> findAllByScheduleId(Long scheduleId) {
    return schedulePollutantRepository.findSchedulePollutantsByScheduleId(scheduleId);
  }
}
