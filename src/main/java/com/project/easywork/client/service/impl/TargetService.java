package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.target.TargetCreateD;
import com.project.easywork.client.domain.dto.target.TargetD;
import com.project.easywork.client.domain.persistance.Prevention;
import com.project.easywork.client.domain.persistance.Target;
import com.project.easywork.client.mapper.TargetMapper;
import com.project.easywork.client.service.ITargetService;
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
  
  private final ITargetDataService targetDataService;
  private final TargetMapper targetMapper;
  
  private final DomainEntityResolver domainEntityResolver;
  
  @Override
  public void registerTargets(List<TargetCreateD> requestDtos, Prevention prevention) {
    if (requestDtos == null || requestDtos.isEmpty()) {
      return;
    }
    
    List<Target> targets = requestDtos.stream()
        .map(dto -> {
          Target target = targetMapper.toEntity(dto);
          target.attachPrevention(prevention);
          return target;
        })
        .toList();
    
    targetDataService.saveAll(targets);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<TargetD> getTargets() {
    return targetMapper.toDtoList(targetDataService.findAll());
  }
  
  @Override
  public void removeTarget(Long preventionId, Long targetId) {
    Prevention prevention = domainEntityResolver.getPreventionOrThrow(preventionId);
    Target target = domainEntityResolver.getTargetOrThrow(targetId);
    prevention.removeTarget(target);
  }
}
