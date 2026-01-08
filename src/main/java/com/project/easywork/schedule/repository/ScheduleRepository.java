package com.project.easywork.schedule.repository;

import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.schedule.domain.ScheduleStatus;
import com.project.easywork.schedule.domain.persistance.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
  @Query("""
    SELECT s
    FROM Schedule s
    JOIN FETCH s.team t
    JOIN FETCH s.stack st
    order by s.measureDate desc
  """)
  List<Schedule> findAllWithTeamAndStack();
  @Query("""
    select s from Schedule s
    join fetch s.stack st
    join fetch st.workplace w
    join fetch w.company c
    where s.id = :id
  """)
  Optional<Schedule> findDetailById(Long id);
  List<Schedule> findByStackIdAndStatus(Long stackId, ScheduleStatus status);
  
  Long stack(Stack stack);
}