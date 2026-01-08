package com.project.easywork.plan.repository;

import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.plan.domain.PlanStatus;
import com.project.easywork.plan.domain.persistance.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
  @Query("""
    SELECT s
    FROM Plan s
    JOIN FETCH s.team t
    JOIN FETCH s.stack st
    order by s.measureDate desc
  """)
  List<Plan> findAllWithTeamAndStack();
  @Query("""
    select s from Plan s
    join fetch s.stack st
    join fetch st.workplace w
    join fetch w.company c
    where s.id = :id
  """)
  Optional<Plan> findDetailById(Long id);
  List<Plan> findByStackIdAndStatus(Long stackId, PlanStatus status);
  
  Long stack(Stack stack);
}