package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.stack.*;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.service_data.IStackDataService;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.client.service.IStackService;
import com.project.easywork.client.validator.StackValidator;
import com.project.easywork.common.resolver.DomainEntityResolver;
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
  
  private final StackValidator stackValidator;
  private final DomainEntityResolver domainEntityResolver;
  
  @Override
  public StackResponseDto registerStack(StackCreateRequestDto dto) {
    stackValidator.validateForCreate(dto);
    Workplace workplace = domainEntityResolver
        .getWorkplaceOrThrow(
            dto.getWorkplaceId()
        );
    
    Stack stack = stackMapper.toEntity(dto);
    stack.attachWorkplace(workplace);
    
    return stackMapper.toDto(stackDataService.save(stack));
  }
  
  @Override
  @Transactional(readOnly = true)
  public StackDetailResponseDto getStack(Long stackId) {
    Stack stack = domainEntityResolver.getStackOrThrow(stackId);
    return stackMapper.toDetailDto(stack);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<StackResponseDto> getStacks() {
    return stackMapper.toDtoList(stackDataService.findAll());
  }
  
  @Override
  public StackResponseDto updateStack(Long stackId, StackUpdateRequestDto dto) {
    stackValidator.validateForUpdate(stackId, dto);
    Stack stack = domainEntityResolver.getStackOrThrow(stackId);
    stackMapper.updateStack(dto, stack);
    return stackMapper.toDto(stack);
  }
  
  @Override
  public void removeStack(Long stackId) {
    domainEntityResolver.getStackOrThrow(stackId);
    stackDataService.deleteById(stackId);
  }
}
