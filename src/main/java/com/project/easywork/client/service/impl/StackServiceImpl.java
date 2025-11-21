package com.project.easywork.client.service.impl;

import com.project.easywork.client.service_data.StackDataService;
import com.project.easywork.client.dto.view.StackDetailDto;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.client.service.StackService;
import com.project.easywork.client.service.WorkplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StackServiceImpl implements StackService {
  
  private final StackDataService stackDataService;
  private final WorkplaceService workplaceService;
  private final StackMapper stackMapper;
  
  @Override
  public StackDetailDto getStack(Long stackId) {
    return stackMapper.toDetailDto(stackDataService.findStackById(stackId));
  }
}
