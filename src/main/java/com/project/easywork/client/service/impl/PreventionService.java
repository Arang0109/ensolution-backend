package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.prevention.PreventionBundleCreateD;
import com.project.easywork.client.domain.dto.prevention.PreventionBundleUpdateD;
import com.project.easywork.client.domain.dto.prevention.PreventionD;
import com.project.easywork.client.domain.dto.prevention.PreventionDetailD;
import com.project.easywork.client.domain.persistance.Prevention;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.mapper.PreventionMapper;
import com.project.easywork.client.service.IFacilityService;
import com.project.easywork.client.service.IPreventionService;
import com.project.easywork.client.service.ITargetService;
import com.project.easywork.client.service_data.IPreventionDataService;
import com.project.easywork.common.resolver.DomainEntityResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PreventionService implements IPreventionService {
  
  private final IPreventionDataService preventionDataService;
  private final PreventionMapper preventionMapper;
  private final IFacilityService facilityService;
  private final ITargetService targetService;
  
  private final DomainEntityResolver domainEntityResolver;
  
  @Override
  public PreventionDetailD registerPreventionBundle(PreventionBundleCreateD dto) {
    Stack stack = domainEntityResolver.getStackOrThrow(dto.getPrevention().getStackId());
    Prevention prevention = preventionMapper.toEntity(dto.getPrevention());
    prevention.attachStack(stack);
    
    preventionDataService.save(prevention);
    
    facilityService.registerFacilities(dto.getFacilities(), prevention);
    targetService.registerTargets(dto.getTargets(), prevention);
    
    return preventionMapper.toDetailDto(prevention);
  }
  
  @Override
  @Transactional(readOnly = true)
  public PreventionDetailD getPrevention(Long preventionId) {
    Prevention prevention = domainEntityResolver.getPreventionOrThrow(preventionId);
    return preventionMapper.toDetailDto(prevention);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<PreventionD> getPreventions() {
    return preventionMapper.toDtoList(preventionDataService.findAll());
  }
  
  @Override
  public List<PreventionD> getPreventionsByStack(Long stackId) {
    return preventionMapper
        .toDtoList(preventionDataService.findPreventionsByStackId(stackId));
  }
  
  @Override
  public PreventionDetailD updatePrevention(Long preventionId, PreventionBundleUpdateD dto) {
    Prevention prevention = domainEntityResolver.getPreventionOrThrow(preventionId);
    prevention.update(dto.getPrevention());
    prevention.updateFacilities(dto.getFacilities());
    prevention.updateTargets(dto.getTargets());
    
    return preventionMapper.toDetailDto(prevention);
  }
  
  @Override
  public void removePrevention(Long preventionId) {
    domainEntityResolver.getPreventionOrThrow(preventionId);
    preventionDataService.deleteById(preventionId);
  }
}