package com.project.easywork.client.service_data.impl;

import com.project.easywork.client.service_data.StackDataService;
import com.project.easywork.client.entity.Stack;
import com.project.easywork.client.repository.StackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StackDataServiceImpl implements StackDataService {
  
  private final StackRepository stackRepository;
  
  @Override
  public Stack findStackById(Long stackId) {
    return stackRepository.findById(stackId)
        .orElseThrow();
  }
}