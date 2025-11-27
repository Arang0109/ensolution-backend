package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.workplace.*;
import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.mapper.WorkplaceMapper;
import com.project.easywork.client.service.IWorkplaceService;
import com.project.easywork.client.service_data.IWorkplaceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkplaceService implements IWorkplaceService {
  
  private final WorkplaceMapper workplaceMapper;
  
  private final IWorkplaceDataService workplaceDataService;
  
  @Override
  public void registerWorkplace(WorkplaceCreateRequestDto requestDto) {
    Workplace workplace = workplaceMapper.toEntityFromWorkplaceCreateDto(requestDto);
    
    workplaceDataService.saveWorkplace(workplace);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<WorkplaceResponseDto> getWorkplaces() {
    return workplaceMapper.toDtoList(workplaceDataService.findAllWorkplaces());
  }
  
  @Override
  @Transactional(readOnly = true)
  public WorkplaceResponseDto getWorkplace(Long workplaceId) {
    return workplaceMapper.toDto(workplaceDataService.findById(workplaceId));
  }
  
  @Override
  public WorkplaceResponseDto updateWorkplace(Long workplaceId, WorkplaceUpdateRequestDto requestDto) {
    Workplace workplace = workplaceDataService.findById(workplaceId);
    workplace.update(requestDto);
    return workplaceMapper.toDto(workplace);
  }
  
  @Override
  public void removeWorkplace(Long workplaceId) {
    workplaceDataService.deleteWorkplace(workplaceId);
  }
}
