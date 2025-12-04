package com.project.easywork.schedule.service_data.impl;

import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.schedule.domain.persistance.Schedule;
import com.project.easywork.schedule.repository.ScheduleRepository;
import com.project.easywork.schedule.service_data.IScheduleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleDataService implements IScheduleDataService {
  
  private final ScheduleRepository scheduleRepository;
  
  @Override
  public Schedule save(Schedule schedule) {
    return scheduleRepository.save(schedule);
  }
  
  @Override
  public void deleteById(Long scheduleId) {
    if (!scheduleRepository.existsById(scheduleId)) {
      throw new CustomException(ErrorCode.NOT_FOUND, " 삭제할 일정이 존재하지 않습니다.");
    }
    scheduleRepository.deleteById(scheduleId);
  }
  
  @Override
  public List<Schedule> findAll() {
    return scheduleRepository.findAll();
  }
  
  @Override
  public List<Schedule> findSchedulesByStackId(Long stackId) {
    return scheduleRepository.findSchedulesByStackId(stackId);
  }
}
