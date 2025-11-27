package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.workplace.*;

import java.util.List;

public interface IWorkplaceService {
  void registerWorkplace(WorkplaceCreateRequestDto requestDto);
  WorkplaceResponseDto getWorkplace(Long workplaceId);
  List<WorkplaceResponseDto> getWorkplaces();
  WorkplaceResponseDto updateWorkplace(Long workplaceId, WorkplaceUpdateRequestDto requestDto);
  void removeWorkplace(Long workplaceId);
}