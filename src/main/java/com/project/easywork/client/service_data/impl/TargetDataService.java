package com.project.easywork.client.service_data.impl;

import com.project.easywork.client.domain.persistance.Target;
import com.project.easywork.client.repository.TargetRepository;
import com.project.easywork.client.service_data.ITargetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TargetDataService implements ITargetDataService {
  
  private final TargetRepository targetRepository;
  
  @Override
  public Target findById(Long targetId) {
    return targetRepository.findById(targetId)
        .orElseThrow();
  }
  
  @Override
  public Target save(Target target) {
    return targetRepository.save(target);
  }
  
  @Override
  public List<Target> saveAll(List<Target> targets) {
    return targetRepository.saveAll(targets);
  }
  
  @Override
  public void deleteById(Long targetId) {
    targetRepository.deleteById(targetId);
  }
  
  @Override
  public List<Target> findAll() {
    return targetRepository.findAll();
  }
  
  @Override
  public List<Target> findTargetsByPreventionId(Long preventionId) {
    return targetRepository.findTargetsByPreventionId(preventionId);
  }
}
