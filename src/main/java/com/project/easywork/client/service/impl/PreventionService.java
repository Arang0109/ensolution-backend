package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.prevention.PreventionCreaterequestDto;
import com.project.easywork.client.domain.dto.prevention.PreventionDetailResponseDto;
import com.project.easywork.client.domain.dto.prevention.PreventionResponseDto;
import com.project.easywork.client.domain.dto.prevention.PreventionUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Prevention;
import com.project.easywork.client.mapper.PreventionMapper;
import com.project.easywork.client.service.IPreventionService;
import com.project.easywork.client.service_data.IPreventionDataService;
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
  
  @Override
  public void registerPrevention(PreventionCreaterequestDto requestDto) {
    Prevention prevention = preventionMapper.toEntityFromPreventionCreateDto(requestDto);
    preventionDataService.save(prevention);
  }
  
  @Override
  @Transactional(readOnly = true)
  public PreventionDetailResponseDto getPrevention(Long preventionId) {
    return null;
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