package com.project.easywork.schedule.service;

import com.project.easywork.schedule.domain.dto.ScheduleMeasurementCreateReqDto;
import com.project.easywork.schedule.domain.dto.ScheduleMeasurementResDto;

import java.util.List;

public interface IScheduleMeasurementService {
  ScheduleMeasurementResDto register(ScheduleMeasurementCreateReqDto dto);
  void registerAll(Long scheduleId, List<Long> measurementIds);
}
