package com.project.easywork.schedule.service_data;

import com.project.easywork.common.constant.ScheduleStatus;
import com.project.easywork.schedule.domain.persistance.Schedule;

import java.util.List;

public interface IScheduleDataService {
  Schedule findById(Long scheduleId);
  Schedule save(Schedule schedule);
  void deleteById(Long scheduleId);
  List<Schedule> findAllWithTeamAndStack();
  List<Schedule> findSchedulesByStackIdAndStatusIn(Long stackId, List<ScheduleStatus> status);
}
