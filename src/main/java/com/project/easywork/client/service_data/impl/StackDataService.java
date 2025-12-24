package com.project.easywork.client.service_data.impl;

import com.project.easywork.client.service_data.IStackDataService;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.repository.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StackDataService implements IStackDataService {
  
  private final StackRepository stackRepository;
  
  @Override
  public Stack findById(Long stackId) {
    return stackRepository.findById(stackId).orElse(null);
  }
  
  @Override
  public Stack save(Stack stack) { return stackRepository.save(stack); }
  
  @Override
  public void deleteById(Long stackId) { stackRepository.deleteById(stackId); }
  
  @Override
    public List<Stack> findAll() {return stackRepository.findAll(); }
  
  @Override
  public List<Stack> findStacksByWorkplaceId(Long workplaceId) {
    return stackRepository.findStacksByWorkplaceId(workplaceId);
  }
}