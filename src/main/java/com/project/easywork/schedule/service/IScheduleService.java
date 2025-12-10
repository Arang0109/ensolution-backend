package com.project.easywork.schedule.service;

import com.project.easywork.common.constant.ScheduleStatus;
import com.project.easywork.schedule.domain.dto.*;

import java.util.List;

public interface IScheduleService {
  List<ScheduleTableViewDto> getList();
  List<ScheduleResponseDto> getListByStack(Long stackId, List<ScheduleStatus> status);
  ScheduleDetailResponseDto getSchedule(Long scheduleId);
  ScheduleResponseDto register(ScheduleCreateRequestDto dto);
  ScheduleResponseDto update(Long scheduleId, ScheduleUpdateRequestDto dto);
  ScheduleResponseDto updateStatus(Long scheduleId, ScheduleStatusUpdateRequestDto dto);
  void delete(Long scheduleId);
}
