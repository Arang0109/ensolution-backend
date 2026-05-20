package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.workplace.WorkplaceCreateD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceDetailD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateD;
import com.project.easywork.client.domain.persistance.Company;
import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.mapper.WorkplaceMapper;
import com.project.easywork.client.service.IWorkplaceService;
import com.project.easywork.client.service_data.IWorkplaceDataService;
import com.project.easywork.client.validator.WorkplaceValidator;
import com.project.easywork.common.resolver.DomainEntityResolver;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkplaceService implements IWorkplaceService {
  
  private final IWorkplaceDataService workplaceDataService;
  private final WorkplaceMapper workplaceMapper;
  
  private final WorkplaceValidator workplaceValidator;
  private final DomainEntityResolver domainEntityResolver;
  
  private final EntityManager entityManager;
  
  @Override
  public WorkplaceD registerWorkplace(WorkplaceCreateD dto) {
    workplaceValidator.validateForCreate(dto);
    Company company = domainEntityResolver.getCompanyOrThrow(dto.getCompanyId());
    Workplace workplace = workplaceMapper.toEntity(dto);
    workplace.attachCompany(company);
    
    return workplaceMapper.toDto(workplaceDataService.save(workplace));
  }
  
  @Override
  @Transactional(readOnly = true)
  public WorkplaceDetailD getWorkplace(Long workplaceId) {
    Workplace workplace = domainEntityResolver.getWorkplaceOrThrow(workplaceId);
    return workplaceMapper.toDetailDto(workplace);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<WorkplaceD> getWorkplaces() {
    return workplaceMapper.toDtoList(workplaceDataService.findAll());
  }
  
  @Override
  public WorkplaceD updateWorkplace(Long workplaceId, WorkplaceUpdateD dto) {
    workplaceValidator.validateForUpdate(workplaceId, dto);
    Workplace workplace = domainEntityResolver.getWorkplaceOrThrow(workplaceId);
    workplace.update(dto);
    
    entityManager.flush();
    
    return workplaceMapper.toDto(workplace);
  }
  
  @Override
  public void removeWorkplace(Long workplaceId) {
    domainEntityResolver.getWorkplaceOrThrow(workplaceId);
    workplaceDataService.deleteById(workplaceId);
  }
}
