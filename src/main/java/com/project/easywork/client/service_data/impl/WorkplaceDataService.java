package com.project.easywork.client.service_data.impl;

import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.repository.StackRepository;
import com.project.easywork.client.repository.WorkplaceRepository;
import com.project.easywork.client.service_data.IWorkplaceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkplaceDataService implements IWorkplaceDataService {
  
  private final WorkplaceRepository workplaceRepository;
  private final StackRepository stackRepository;
  
  @Override
  public Workplace findById(Long workplaceId) {
    return workplaceRepository.findById(workplaceId)
        .orElseThrow();
  }
  
  @Override
  public void saveWorkplace(Workplace workplace) {
    workplaceRepository.save(workplace);
  }
  
  @Override
  public void deleteWorkplace(Long workplaceId) {
    workplaceRepository.deleteById(workplaceId);
  }
  
  @Override
  public List<Workplace> findAllWorkplaces() {
    return workplaceRepository.findAll();
  }
  
  @Override
  public List<Workplace> findWorkplacesByCompanyId(Long CompanyId) {
    return workplaceRepository.findWorkplacesByCompanyId(CompanyId);
  }
}
