package com.project.easywork.client.service;

import com.project.easywork.client.dto.WorkplaceDto;
import com.project.easywork.client.dto.list.WorkplaceProfileDto;
import com.project.easywork.client.dto.view.WorkplaceDetailDto;
import com.project.easywork.client.dto.update.WorkplaceUpdateDto;
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