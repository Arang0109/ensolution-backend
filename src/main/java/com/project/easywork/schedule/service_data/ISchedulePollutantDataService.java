package com.project.easywork.schedule.service_data;

import com.project.easywork.schedule.domain.persistance.SchedulePollutant;

import java.util.List;

public interface ISchedulePollutantDataService {
  SchedulePollutant save(SchedulePollutant schedulePollutant);
  List<SchedulePollutant> findAllByScheduleId(Long scheduleId);
}
