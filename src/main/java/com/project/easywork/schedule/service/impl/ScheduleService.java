package com.project.easywork.schedule.service.impl;

import com.project.easywork.schedule.domain.dto.ScheduleCreateRequestDto;
import com.project.easywork.schedule.domain.dto.ScheduleResponseDto;
import com.project.easywork.schedule.domain.dto.ScheduleStatusUpdateRequestDto;
import com.project.easywork.schedule.domain.dto.ScheduleUpdateRequestDto;
import com.project.easywork.schedule.service.IScheduleService;
import com.project.easywork.schedule.service_data.IScheduleDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService implements IScheduleService {
  
  private final IScheduleDataService scheduleDataService;
  
  @Override
  public List<ScheduleResponseDto> getList() {
    return List.of();
  }
  
  @Override
  public List<ScheduleResponseDto> getListByStack() {
    return List.of();
  }
  
  @Override
  public ScheduleResponseDto register(ScheduleCreateRequestDto dto) {
    return null;
  }
  
  @Override
  public ScheduleResponseDto update(ScheduleUpdateRequestDto dto) {
    return null;
  }
  
  @Override
  public ScheduleResponseDto updateStatus(ScheduleStatusUpdateRequestDto dto) {
    return null;
  }
  
  @Override
  public void delete(Long scheduleId) {
  
  }
}
