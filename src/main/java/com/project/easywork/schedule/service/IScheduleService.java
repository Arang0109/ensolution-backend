package com.project.easywork.schedule.service;

import com.project.easywork.schedule.domain.ScheduleStatus;
import com.project.easywork.schedule.domain.dto.*;

import java.util.List;

public interface IScheduleService {
  List<ScheduleTableViewDto> getList();
  List<ScheduleResDto> getListByStack(Long stackId, List<ScheduleStatus> status);
  ScheduleDetailResDto getSchedule(Long scheduleId);
  ScheduleResDto register(ScheduleCreateReqDto scheduleCreateReqDto);
  void addMeasurement(Long scheduleId, List<ScheduleMeasurementCreateReqDto> dto);
  ScheduleResDto update(Long scheduleId, ScheduleUpdateReqDto dto);
  ScheduleResDto updateStatus(Long scheduleId, ScheduleStatusUpdateReqDto dto);
  void delete(Long scheduleId);
}
