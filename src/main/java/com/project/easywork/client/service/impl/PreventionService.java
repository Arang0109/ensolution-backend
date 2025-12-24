package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.prevention.*;
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
  public PreventionDetailResponseDto registerPreventionBundle(PreventionBundleCreateRequestDto requestDto) {
    
    // stack 엔티티 불러오기 -> prevention 연관 엔티티 설정 -> 저장
    Stack stack = domainEntityResolver.getStackOrThrow(requestDto.getPrevention().getStackId());
    Prevention prevention = preventionMapper.toEntity(requestDto.getPrevention());
    prevention.attachStack(stack);
    Prevention savedPrevention = preventionDataService.save(prevention);
    
    if (requestDto.getFacilities() != null) {
      facilityService.registerFacilities(
          requestDto.getFacilities(),
          savedPrevention
      );
    }
    
    if (requestDto.getTargets() != null) {
      targetService.registerTargets(
          requestDto.getTargets(),
          savedPrevention
      );
    }
    
    return preventionMapper.toDetailDto(savedPrevention);
  }
  
  @Override
  @Transactional(readOnly = true)
  public PreventionDetailResponseDto getPrevention(Long preventionId) {
    Prevention prevention = domainEntityResolver.getPreventionOrThrow(preventionId);
    return preventionMapper.toDetailDto(prevention);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<PreventionResponseDto> getPreventions() {
    return preventionMapper.toDtoList(preventionDataService.findAll());
  }
  
  @Override
  public List<PreventionResponseDto> getPreventionsByStack(Long stackId) {
    return preventionMapper
        .toDtoList(preventionDataService.findPreventionsByStackId(stackId));
  }
  
  @Override
  public PreventionResponseDto updatePrevention(Long preventionId, PreventionUpdateRequestDto requestDto) {
    Prevention prevention = domainEntityResolver.getPreventionOrThrow(preventionId);
    preventionMapper.updatePrevention(requestDto, prevention);
    return preventionMapper.toDto(prevention);
  }
  
  @Override
  public void removePrevention(Long preventionId) {
    domainEntityResolver.getPreventionOrThrow(preventionId);
    preventionDataService.deleteById(preventionId);
  }
}