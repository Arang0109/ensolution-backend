package com.project.easywork.schedule.service_data;

import com.project.easywork.schedule.domain.persistance.ScheduleMeasurement;

import java.util.List;

public interface IScheduleMeasurementDataService {
  ScheduleMeasurement save(ScheduleMeasurement scheduleMeasurement);
  void saveAll(List<ScheduleMeasurement> scheduleMeasurements);
  List<ScheduleMeasurement> findAllByScheduleId(Long scheduleId);
}
