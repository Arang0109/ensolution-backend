package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.target.TargetCreateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetResponseDto;
import com.project.easywork.client.domain.dto.target.TargetUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Target;
import com.project.easywork.client.mapper.TargetMapper;
import com.project.easywork.client.service.ITargetService;
import com.project.easywork.client.service_data.impl.TargetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TargetService implements ITargetService {
  
  private final TargetDataService targetDataService;
  private final TargetMapper targetMapper;
  
  @Override
  public void registerTarget(TargetCreateRequestDto requestDto) {
    Target target = targetMapper.toEntityFromFacilityCreateDto(requestDto);
    targetDataService.save(target);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<TargetResponseDto> getTargets() {
    return targetMapper.toDtoList(targetDataService.findAll());
  }
  
  @Override
  public TargetResponseDto updateTarget(Long targetId, TargetUpdateRequestDto requestDto) {
    Target target = targetDataService.findById(targetId);
    target.update(requestDto);
    return targetMapper.toDto(target);
  }
  
  @Override
  public void removeTarget(Long targetId) {
    targetDataService.deleteById(targetId);
  }
}
