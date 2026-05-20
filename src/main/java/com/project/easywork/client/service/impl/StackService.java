package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.stack.StackCreateD;
import com.project.easywork.client.domain.dto.stack.StackD;
import com.project.easywork.client.domain.dto.stack.StackDetailD;
import com.project.easywork.client.domain.dto.stack.StackUpdateD;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.Workplace;
import com.project.easywork.client.service_data.IStackDataService;
import com.project.easywork.client.mapper.StackMapper;
import com.project.easywork.client.service.IStackService;
import com.project.easywork.client.validator.StackValidator;
import com.project.easywork.common.file.CsvReader;
import com.project.easywork.common.file.UploadFileValidator;
import com.project.easywork.common.resolver.DomainEntityResolver;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StackService implements IStackService {
  
  private final IStackDataService stackDataService;
  private final StackMapper stackMapper;
  
  private final StackValidator stackValidator;
  private final DomainEntityResolver domainEntityResolver;
  
  private final UploadFileValidator uploadFileValidator;
  private final CsvReader csvReader;
  
  private final EntityManager entityManager;
  
  @Override
  public StackD registerStack(StackCreateD dto) {
    stackValidator.validateForCreate(dto);
    Workplace workplace = domainEntityResolver.getWorkplaceOrThrow
        (
            dto.getWorkplaceId()
        );
    
    Stack stack = stackMapper.toEntity(dto);
    stack.attachWorkplace(workplace);
    
    return stackMapper.toDto(stackDataService.save(stack));
  }
  
  @Override
  @Transactional(readOnly = true)
  public StackDetailD getStack(Long stackId) {
    Stack stack = domainEntityResolver.getStackOrThrow(stackId);
    return stackMapper.toDetailDto(stack);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<StackD> getStacks() {
    return stackMapper.toDtoList(stackDataService.findAll());
  }
  
  @Override
  public StackD updateStack(Long stackId, StackUpdateD dto) {
    stackValidator.validateForUpdate(stackId, dto);
    Stack stack = domainEntityResolver.getStackOrThrow(stackId);
    stack.update(dto);
    
    entityManager.flush();
    
    return stackMapper.toDto(stack);
  }
  
  @Override
  public void removeStack(Long stackId) {
    domainEntityResolver.getStackOrThrow(stackId);
    stackDataService.deleteById(stackId);
  }
  
  @Override
  public void importStacks(MultipartFile file) {
    uploadFileValidator.validate(file);
    
    List<String[]> rows = csvReader.read(file);
    
    rows.forEach(row -> Arrays.stream(row).forEach(System.out::println));
  }
}
