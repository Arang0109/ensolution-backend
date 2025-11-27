package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.workplace.WorkplaceDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceProfileDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceDetailDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WorkplaceService {
  @Transactional void registerWorkplace(WorkplaceDto request);
  List<WorkplaceProfileDto> getWorkplaces();
  WorkplaceDetailDto getWorkplace(Long workplaceId);
  @Transactional
  WorkplaceUpdateDto updateWorkplaceProfile(Long workplaceId, WorkplaceUpdateDto companyDto);
  void removeWorkplace(Long workplaceId);
}