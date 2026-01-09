package com.project.easywork.plan.service_data.impl;

import com.project.easywork.plan.domain.PlanStatus;
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.plan.domain.persistance.Plan;
import com.project.easywork.plan.repository.PlanRepository;
import com.project.easywork.plan.service_data.IPlanDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanDataService implements IPlanDataService {
  
  private final PlanRepository planRepository;
  
  @Override
  public Plan findById(Long scheduleId) {
    return planRepository.findById(scheduleId).orElseThrow(
        () -> new CustomException(ErrorCode.NOT_FOUND, "해당 측정계획이 존재하지 않습니다.")
    );
  }
  
  @Override
  public Plan findDetailById(Long scheduleId) {
    return planRepository.findDetailById(scheduleId).orElseThrow(
        () -> new CustomException(ErrorCode.NOT_FOUND, "해당 측정계획이 존재하지 않습니다.")
    );
  }
  
  @Override
  public Plan save(Plan plan) {
    try {
      return planRepository.save(plan);
    } catch (DataIntegrityViolationException e) {
      throw new CustomException(ErrorCode.SCHEDULE_ALREADY_EXISTS, "이미 해당 사업장·팀·측정일에 등록된 일정이 존재합니다.");
    }
  }
  
  @Override
  public void deleteById(Long scheduleId) {
    if (!planRepository.existsById(scheduleId)) {
      throw new CustomException(ErrorCode.NOT_FOUND, " 삭제할 측정계획이 존재하지 않습니다.");
    }
    planRepository.deleteById(scheduleId);
  }
  
  @Override
  public List<Plan> findAllWithTeamAndStack() {
    return planRepository.findAllWithTeamAndStack();
  }
  
  @Override
  public List<Plan> findCompletedByStackId(Long stackId) {
    return planRepository.findByStackIdAndStatus(stackId, PlanStatus.COMPLETED);
  }
}
