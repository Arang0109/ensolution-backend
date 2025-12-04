package com.project.easywork.schedule.service_data;

import com.project.easywork.schedule.domain.persistance.Schedule;

import java.util.List;

public interface IScheduleDataService {
  Schedule findById(Long scheduleId);
  Schedule save(Schedule schedule);
  void deleteById(Long scheduleId);
  List<Schedule> findAll();
  List<Schedule> findSchedulesByStackId(Long stackId);
}
