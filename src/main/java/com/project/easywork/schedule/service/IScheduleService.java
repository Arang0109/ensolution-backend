package com.project.easywork.schedule.service;

import com.project.easywork.schedule.domain.dto.ScheduleCreateRequestDto;
import com.project.easywork.schedule.domain.dto.ScheduleResponseDto;
import com.project.easywork.schedule.domain.dto.ScheduleStatusUpdateRequestDto;
import com.project.easywork.schedule.domain.dto.ScheduleUpdateRequestDto;

import java.util.List;

public interface IScheduleService {
  List<ScheduleResponseDto> getList();
  List<ScheduleResponseDto> getListByStack();
  ScheduleResponseDto register(ScheduleCreateRequestDto dto);
  ScheduleResponseDto update(ScheduleUpdateRequestDto dto);
  ScheduleResponseDto updateStatus(ScheduleStatusUpdateRequestDto dto);
  void delete(Long scheduleId);
}
