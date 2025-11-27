package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.prevention.PreventionResponseDto;
import com.project.easywork.client.domain.dto.stack.*;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.service_data.IStackDataService;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.client.service.IStackService;
import com.project.easywork.client.service.IWorkplaceService;
import com.project.easywork.client.service_data.impl.StackDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StackService implements IStackService {
  
  private final IStackDataService stackDataService;
  private final StackMapper stackMapper;
  
  @Override
  public void registerStack(StackCreateRequestDto requestDto) {
    Stack stack = stackMapper.toEntityFromStackCreateDto(requestDto);
    stackDataService.saveStack(stack);
  }
  
  @Override
  @Transactional(readOnly = true)
  public StackDetailResponseDto getStack(Long stackId) {
    StackResponseDto stack = stackMapper.toDto(stackDataService.findById(stackId));
    List<PreventionResponseDto> preventions = null;
    return StackDetailResponseDto.builder()
        .stack(stack)
        .preventions(preventions)
        .build();
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<StackResponseDto> getStacks() {
    return stackMapper.toDtoList(stackDataService.findAllStacks());
  }
  
  @Override
  public StackResponseDto updateStack(Long stackId, StackUpdateRequestDto requestDto) {
    Stack stack = stackDataService.findById(stackId);
    stack.update(requestDto);
    return stackMapper.toDto(stack);
  }
  
  @Override
  public void removeStack(Long stackId) {
    stackDataService.deleteStack(stackId);
  }
}
