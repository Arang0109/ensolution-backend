package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.facility.FacilityResponseDto;
import com.project.easywork.client.domain.dto.prevention.*;
import com.project.easywork.client.domain.dto.target.TargetResponseDto;
import com.project.easywork.client.domain.persistance.Prevention;
import com.project.easywork.client.mapper.FacilityMapper;
import com.project.easywork.client.mapper.PreventionMapper;
import com.project.easywork.client.mapper.TargetMapper;
import com.project.easywork.client.service.IFacilityService;
import com.project.easywork.client.service.IPreventionService;
import com.project.easywork.client.service.ITargetService;
import com.project.easywork.client.service_data.IPreventionDataService;
import com.project.easywork.client.service_data.IFacilityDataService;
import com.project.easywork.client.service_data.ITargetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PreventionService implements IPreventionService {
  
  private final IPreventionDataService preventionDataService;
  private final PreventionMapper preventionMapper;
  private final FacilityMapper facilityMapper;
  private final IFacilityDataService facilityDataService;
  private final IFacilityService facilityService;
  private final TargetMapper targetMapper;
  private final ITargetDataService targetDataService;
  private final ITargetService targetService;
  
  @Override
  public PreventionDetailResponseDto registerPreventionBundle(PreventionBundleCreateRequestDto requestDto) {
    
    Prevention savedPrevention = preventionDataService.save(
        preventionMapper.toEntityFromPreventionCreateDto(requestDto.getPrevention())
    );
    Long preventionId = savedPrevention.getId();
    
    List<FacilityResponseDto> facilityDtos = Optional.ofNullable(requestDto.getFacilities())
        .map(list -> {
          list.forEach(dto -> dto.setPreventionId(preventionId));
          return facilityService.registerFacilities(list);
        })
        .orElse(List.of());
    
    List<TargetResponseDto> targetDtos = Optional.ofNullable(requestDto.getTargets())
        .map(list -> {
          list.forEach(dto -> dto.setPreventionId(preventionId));
          return targetService.registerTargets(list);
        })
        .orElse(List.of());
    
    return PreventionDetailResponseDto.builder()
        .prevention(preventionMapper.toDto(savedPrevention))
        .facilities(facilityDtos)
        .targets(targetDtos)
        .build();
  }
  
  @Override
  @Transactional(readOnly = true)
  public PreventionDetailResponseDto getPrevention(Long preventionId) {
    PreventionResponseDto prevention = preventionMapper.toDto(preventionDataService.findById(preventionId));
    List<FacilityResponseDto> facilities = facilityMapper
        .toDtoList(facilityDataService.findFacilitiesByPreventionId(preventionId));
    List<TargetResponseDto> targets = targetMapper
        .toDtoList(targetDataService.findTargetsByPreventionId(preventionId));
    return PreventionDetailResponseDto.builder()
        .prevention(prevention)
        .facilities(facilities)
        .targets(targets)
        .build();
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<PreventionResponseDto> getPreventions() {
    return preventionMapper.toDtoList(preventionDataService.findAll());
  }
  
  @Override
  public List<PreventionResponseDto> getPreventionsByStackId(Long stackId) {
    return preventionMapper
        .toDtoList(preventionDataService.findPreventionsByStackId(stackId));
  }
  
  @Override
  public PreventionResponseDto updatePrevention(Long preventionId, PreventionUpdateRequestDto requestDto) {
    Prevention prevention = preventionDataService.findById(preventionId);
    prevention.update(requestDto);
    return preventionMapper.toDto(prevention);
  }
  
  @Override
  public void removePrevention(Long preventionId) {
    preventionDataService.deleteById(preventionId);
  }
}