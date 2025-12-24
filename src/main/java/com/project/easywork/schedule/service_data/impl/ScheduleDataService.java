package com.project.easywork.schedule.service_data.impl;

import com.project.easywork.schedule.domain.ScheduleStatus;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.schedule.domain.persistance.Schedule;
import com.project.easywork.schedule.repository.ScheduleRepository;
import com.project.easywork.schedule.service_data.IScheduleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleDataService implements IScheduleDataService {
  
  private final ScheduleRepository scheduleRepository;
  
  @Override
  public Schedule findById(Long scheduleId) {
    return scheduleRepository.findById(scheduleId).orElseThrow(
        () -> new CustomException(ErrorCode.NOT_FOUND, "해당 측정 일정이 존재하지 않습니다.")
    );
  }
  
  @Override
  public Schedule findDetailById(Long scheduleId) {
    return scheduleRepository.findDetailById(scheduleId).orElseThrow(
        () -> new CustomException(ErrorCode.NOT_FOUND, "해당 측정 일정이 존재하지 않습니다.")
    );
  }
  
  @Override
  public Schedule save(Schedule schedule) {
    try {
      return scheduleRepository.save(schedule);
    } catch (DataIntegrityViolationException e) {
      throw new CustomException(ErrorCode.SCHEDULE_ALREADY_EXISTS, "이미 해당 사업장·팀·측정일에 등록된 일정이 존재합니다.");
    }
  }
  
  @Override
  public void deleteById(Long scheduleId) {
    if (!scheduleRepository.existsById(scheduleId)) {
      throw new CustomException(ErrorCode.NOT_FOUND, " 삭제할 일정이 존재하지 않습니다.");
    }
    scheduleRepository.deleteById(scheduleId);
  }
  
  @Override
  public List<Schedule> findAllWithTeamAndStack() {
    return scheduleRepository.findAllWithTeamAndStack();
  }
  
  @Override
  public List<Schedule> findSchedulesByStackIdAndStatusIn(Long stackId, List<ScheduleStatus> status) {
    return scheduleRepository.findSchedulesByStackIdAndStatusIn(stackId, status);
  }
}
