package com.project.easywork.client.service_data.impl;

import com.project.easywork.client.domain.persistance.Prevention;
import com.project.easywork.client.repository.PreventionRepository;
import com.project.easywork.client.service_data.IPreventionDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreventionDataService implements IPreventionDataService {
  
  private final PreventionRepository preventionRepository;
  
  @Override
  public Prevention findById(Long preventionId) {
    return preventionRepository.findById(preventionId).orElse(null);
  }
  
  @Override
  public Prevention save(Prevention prevention) {
    return preventionRepository.save(prevention);
  }
  
  @Override
  public void deleteById(Long preventionId) {
    preventionRepository.deleteById(preventionId);
  }
  
  @Override
  public List<Prevention> findAll() {
    return preventionRepository.findAll();
  }
  
  @Override
  public List<Prevention> findPreventionsByStackId(Long stackId) {
    return preventionRepository.findPreventionsByStackId(stackId);
  }
}
