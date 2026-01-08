package com.project.easywork.schedule.service;

import com.project.easywork.client.domain.dto.stack.MeasurementHistoryD;
import com.project.easywork.schedule.domain.dto.*;

import java.util.List;

public interface IScheduleService {
  List<ScheduleTableViewDto> getList();
  List<MeasurementHistoryD> getListByStack(Long stackId);
  ScheduleDetailResDto getSchedule(Long scheduleId);
  ScheduleResDto register(ScheduleCreateReqDto scheduleCreateReqDto);
  void addMeasurements(Long scheduleId, List<ScheduleMeasurementCreateReqDto> dto);
  ScheduleResDto update(Long scheduleId, ScheduleUpdateReqDto dto);
  ScheduleResDto updateStatus(Long scheduleId, ScheduleStatusUpdateReqDto dto);
  void delete(Long scheduleId);
}
