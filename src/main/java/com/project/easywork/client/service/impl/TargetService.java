package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.target.TargetCreateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetResponseDto;
import com.project.easywork.client.domain.dto.target.TargetUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Prevention;
import com.project.easywork.client.domain.persistance.Target;
import com.project.easywork.client.mapper.TargetMapper;
import com.project.easywork.client.service.ITargetService;
import com.project.easywork.client.service_data.IPreventionDataService;
import com.project.easywork.client.service_data.ITargetDataService;
import com.project.easywork.common.resolver.DomainEntityResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TargetService implements ITargetService {
  
  private final IPreventionDataService preventionDataService;
  private final ITargetDataService targetDataService;
  private final TargetMapper targetMapper;
  
  private final DomainEntityResolver domainEntityResolver;
  
  @Override
  public TargetResponseDto registerTarget(TargetCreateRequestDto requestDto) {
    
    Prevention prevention = domainEntityResolver.getPreventionOrThrow(requestDto.getPreventionId());
    Target target = targetMapper.toEntity(requestDto);
    target.attachPrevention(prevention);
    
    return targetMapper.toDto(targetDataService.save(target));
  }
  
  @Override
  public List<TargetResponseDto> registerTargets(List<TargetCreateRequestDto> requestDtos, Prevention prevention) {
    if (requestDtos == null || requestDtos.isEmpty()) {
      return List.of();
    }
    
    List<Target> targets = requestDtos.stream()
        .map(dto -> {
          Target target = targetMapper.toEntity(dto);
          target.attachPrevention(prevention);
          return target;
        })
        .toList();
    
    return targetDataService.saveAll(targets).stream()
        .map(targetMapper::toDto)
        .toList();
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<TargetResponseDto> getTargets() {
    return targetMapper.toDtoList(targetDataService.findAll());
  }
  
  @Override
  public TargetResponseDto updateTarget(Long targetId, TargetUpdateRequestDto requestDto) {
    Target target = domainEntityResolver.getTargetOrThrow(targetId);
    targetMapper.updateTarget(requestDto, target);
    return targetMapper.toDto(target);
  }
  
  @Override
  public void removeTarget(Long targetId) {
    domainEntityResolver.getTargetOrThrow(targetId);
    targetDataService.deleteById(targetId);
  }
}
