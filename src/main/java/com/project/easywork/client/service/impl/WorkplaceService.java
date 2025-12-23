package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.workplace.*;
import com.project.easywork.client.domain.persistance.Company;
import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.mapper.WorkplaceMapper;
import com.project.easywork.client.service.IWorkplaceService;
import com.project.easywork.client.service_data.ICompanyDataService;
import com.project.easywork.client.service_data.IWorkplaceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkplaceService implements IWorkplaceService {
  
  private final ICompanyDataService companyDataService;
  private final IWorkplaceDataService workplaceDataService;
  private final WorkplaceMapper workplaceMapper;
  
  @Override
  public WorkplaceResponseDto registerWorkplace(WorkplaceCreateRequestDto requestDto) {
    
    Company company = companyDataService.findById(requestDto.getCompanyId());
    Workplace workplace = workplaceMapper.toEntity(requestDto);
    workplace.attachCompany(company);
    
    return workplaceMapper.toDto(workplaceDataService.save(workplace));
  }
  
  @Override
  @Transactional(readOnly = true)
  public WorkplaceDetailResponseDto getWorkplace(Long workplaceId) {
    return workplaceMapper.toDetailDto(workplaceDataService.findById(workplaceId));
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<WorkplaceResponseDto> getWorkplaces() {
    return workplaceMapper.toDtoList(workplaceDataService.findAll());
  }
  
  @Override
  public WorkplaceResponseDto updateWorkplace(Long workplaceId, WorkplaceUpdateRequestDto requestDto) {
    Workplace workplace = workplaceDataService.findById(workplaceId);
    workplaceMapper.updateWorkplace(requestDto, workplace);
    return workplaceMapper.toDto(workplace);
  }
  
  @Override
  public void removeWorkplace(Long workplaceId) {
    workplaceDataService.deleteById(workplaceId);
  }
}
