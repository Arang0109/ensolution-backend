package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.stack.StackResponseDto;
import com.project.easywork.client.domain.dto.workplace.*;
import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.client.mapper.WorkplaceMapper;
import com.project.easywork.client.service.IWorkplaceService;
import com.project.easywork.client.service_data.IStackDataService;
import com.project.easywork.client.service_data.IWorkplaceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkplaceService implements IWorkplaceService {
  
  private final IWorkplaceDataService workplaceDataService;
  private final IStackDataService stackDataService;
  private final WorkplaceMapper workplaceMapper;
  private final StackMapper stackMapper;
  
  @Override
  public WorkplaceResponseDto registerWorkplace(WorkplaceCreateRequestDto requestDto) {
    Workplace workplace = workplaceMapper.toEntityFromWorkplaceCreateDto(requestDto);
    
    return workplaceMapper.toDto(workplaceDataService.save(workplace));
  }
  
  @Override
  @Transactional(readOnly = true)
  public WorkplaceDetailResponseDto getWorkplace(Long workplaceId) {
    WorkplaceResponseDto workplace = workplaceMapper.toDto(workplaceDataService.findById(workplaceId));
    List<StackResponseDto> stacks = stackMapper.toDtoList(stackDataService.findStacksByWorkplaceId(workplaceId));
    
    return WorkplaceDetailResponseDto.builder()
        .workplace(workplace)
        .stacks(stacks)
        .build();
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<WorkplaceResponseDto> getWorkplaces() {
    return workplaceMapper.toDtoList(workplaceDataService.findAll());
  }
  
  @Override
  public WorkplaceResponseDto updateWorkplace(Long workplaceId, WorkplaceUpdateRequestDto requestDto) {
    Workplace workplace = workplaceDataService.findById(workplaceId);
    workplace.update(requestDto);
    return workplaceMapper.toDto(workplace);
  }
  
  @Override
  public void removeWorkplace(Long workplaceId) {
    workplaceDataService.deleteById(workplaceId);
  }
}
