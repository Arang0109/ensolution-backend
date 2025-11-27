package com.project.easywork.client.service_data.impl;

import com.project.easywork.client.service_data.WorkplaceDataService;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.repository.StackRepository;
import com.project.easywork.client.repository.WorkplaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkplaceDataServiceImpl implements WorkplaceDataService {
  
  private final WorkplaceRepository workplaceRepository;
  private final StackRepository stackRepository;
  
  @Override
  public Workplace findByWorkplaceId(Long id) {
    Workplace workplace = workplaceRepository.findById(id)
        .orElseThrow();
    
    List<Stack> sortedStacks =
        stackRepository.findByWorkplace_WorkplaceIdOrderByStackNameAsc(id);
    
    workplace.setStacks(sortedStacks);
    
    return workplace;
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
}
