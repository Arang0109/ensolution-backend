package com.project.easywork.measurement.service;

import com.project.easywork.measurement.domain.dto.command.SaveDraftCommandD;
import com.project.easywork.measurement.domain.dto.command.StatusUpdateCommandD;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;

public interface IMeasurementService {
  
  // 측정 Draft 최초 생성 (Plan 기반)
  void createDraft(Long planId, PlanCreateBundleD command);
  
  // Draft 임시 저장
  void saveDraft(Long planId, SaveDraftCommandD command);
  
  // Draft → 최종 제출 (측정 완료 처리)
  void submitDocument(Long planId);
  
  // 문서 상태 변경 (예: DRAFT → COMPLETED)
  void updateStatus(Long planId, StatusUpdateCommandD command);
  
  // Draft 삭제
  void deleteDraft(Long planId);
  
}
