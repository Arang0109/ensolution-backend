package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.mapper.WorkplaceMapper;
import com.project.easywork.client.service_data.WorkplaceDataService;
import com.project.easywork.client.domain.dto.workplace.WorkplaceDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceProfileDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceDetailDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateDto;
import com.project.easywork.client.service.WorkplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkplaceServiceImpl implements WorkplaceService {
  
  private final WorkplaceMapper workplaceMapper;
  
  private final WorkplaceDataService workplaceDataService;
  
  @Override
  public void registerWorkplace(WorkplaceDto request) {
    Workplace workplace = workplaceMapper.toEntity(request);
    
    workplaceDataService.saveWorkplace(workplace);
  }
  
  @Override
  public List<WorkplaceProfileDto> getWorkplaces() {
//    return workplaceMapper.toProfileDtoList(workplaceDataService.findAllWorkplaces());
    return null;
  }
  
  @Override
  public WorkplaceDetailDto getWorkplace(Long workplaceId) {
    return workplaceMapper.toDetailDto(findWorkplaceById(workplaceId));
  }
  
  @Override
  public WorkplaceUpdateDto updateWorkplaceProfile(Long workplaceId, WorkplaceUpdateDto workplaceDto) {
    Workplace workplace = findWorkplaceById(workplaceId);
    workplace.update(workplaceDto);
    workplaceDataService.saveWorkplace(workplace);
    return workplaceMapper.toUpdateDto(workplace);
  }
  
  @Override
  public void removeWorkplace(Long workplaceId) {
    workplaceDataService.deleteWorkplace(workplaceId);
  }
  
  private Workplace findWorkplaceById(Long id) {
      return workplaceDataService.findByWorkplaceId(id);
  }
}
