package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.facility.FacilityResponseDto;
import com.project.easywork.client.domain.dto.prevention.PreventionCreateRequestDto;
import com.project.easywork.client.domain.dto.prevention.PreventionDetailResponseDto;
import com.project.easywork.client.domain.dto.prevention.PreventionResponseDto;
import com.project.easywork.client.domain.dto.prevention.PreventionUpdateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetResponseDto;
import com.project.easywork.client.domain.persistance.Prevention;
import com.project.easywork.client.mapper.FacilityMapper;
import com.project.easywork.client.mapper.PreventionMapper;
import com.project.easywork.client.mapper.TargetMapper;
import com.project.easywork.client.service.IPreventionService;
import com.project.easywork.client.service_data.IPreventionDataService;
import com.project.easywork.client.service_data.impl.FacilityDataService;
import com.project.easywork.client.service_data.impl.TargetDataService;
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
  private final FacilityMapper facilityMapper;
  private final FacilityDataService facilityDataService;
  private final TargetMapper targetMapper;
  private final TargetDataService targetDataService;
  
  @Override
  public void registerPrevention(PreventionCreateRequestDto requestDto) {
    Prevention prevention = preventionMapper.toEntityFromPreventionCreateDto(requestDto);
    preventionDataService.save(prevention);
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