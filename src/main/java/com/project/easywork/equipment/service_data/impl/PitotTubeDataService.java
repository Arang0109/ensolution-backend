package com.project.easywork.equipment.service_data.impl;

import com.project.easywork.equipment.domain.persistance.PitotTube;
import com.project.easywork.equipment.repository.PitotTubeRepository;
import com.project.easywork.equipment.service_data.IPitotTubeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PitotTubeDataService implements IPitotTubeDataService {
  
  private final PitotTubeRepository pitotTubeRepository;
  
  @Override
  public List<PitotTube> findAll() {
    return pitotTubeRepository.findAll();
  }
}
