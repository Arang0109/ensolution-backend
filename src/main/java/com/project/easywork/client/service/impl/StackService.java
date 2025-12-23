package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.stack.*;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.service_data.IStackDataService;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.client.service.IStackService;
import com.project.easywork.client.service_data.IWorkplaceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StackService implements IStackService {
  
  private final IWorkplaceDataService workplaceDataService;
  private final IStackDataService stackDataService;
  private final StackMapper stackMapper;
  
  @Override
  public StackResponseDto registerStack(StackCreateRequestDto requestDto) {
    
    Workplace workplace = workplaceDataService.findById(requestDto.getWorkplaceId());
    Stack stack = stackMapper.toEntity(requestDto);
    stack.attachWorkplace(workplace);
    
    return stackMapper.toDto(stackDataService.save(stack));
  }
  
  @Override
  @Transactional(readOnly = true)
  public StackDetailResponseDto getStack(Long stackId) {
    return stackMapper.toDetailDto(stackDataService.findById(stackId));
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<StackResponseDto> getStacks() {
    return stackMapper.toDtoList(stackDataService.findAll());
  }
  
  @Override
  public StackResponseDto updateStack(Long stackId, StackUpdateRequestDto requestDto) {
    Stack stack = stackDataService.findById(stackId);
    stackMapper.updateStack(requestDto, stack);
    return stackMapper.toDto(stack);
  }
  
  @Override
  public void removeStack(Long stackId) {
    stackDataService.deleteById(stackId);
  }
}
