package com.project.easywork.client.service_data.impl;

import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.repository.WorkplaceRepository;
import com.project.easywork.client.service_data.IWorkplaceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkplaceDataService implements IWorkplaceDataService {
  
  private final WorkplaceRepository workplaceRepository;
  
  @Override
  public Workplace findById(Long workplaceId) {
    return workplaceRepository.findById(workplaceId)
        .orElseThrow();
  }
  
  @Override
  public Workplace save(Workplace workplace) {
    return workplaceRepository.save(workplace);
  }
  
  @Override
  public void deleteById(Long workplaceId) {
    workplaceRepository.deleteById(workplaceId);
  }
  
  @Override
  public List<Workplace> findAll() {
    return workplaceRepository.findAll();
  }
  
  @Override
  public List<Workplace> findWorkplacesByCompanyId(Long CompanyId) {
    return workplaceRepository.findWorkplacesByCompanyId(CompanyId);
  }
}
