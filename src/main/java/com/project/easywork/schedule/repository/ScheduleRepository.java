package com.project.easywork.schedule.repository;

import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.common.constant.ScheduleStatus;
import com.project.easywork.schedule.domain.persistance.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  List<Schedule> findSchedulesByStackIdAndStatusIn(Long stackId, List<ScheduleStatus> status);
  
  Long stack(Stack stack);
}