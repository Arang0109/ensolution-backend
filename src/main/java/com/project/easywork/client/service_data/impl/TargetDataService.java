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
    return targetRepository.findById(targetId).orElse(null);
  }
  
  @Override
  public Target save(Target target) {
    return targetRepository.save(target);
  }
  
  @Override
  public void saveAll(List<Target> targets) {
    targetRepository.saveAll(targets);
  }
  
  @Override
  public List<Target> findAll() {
    return targetRepository.findAll();
  }
}
