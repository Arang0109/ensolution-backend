package com.project.easywork.pollutant.service_data.impl;

import com.project.easywork.pollutant.domain.persistance.Pollutant;
import com.project.easywork.pollutant.repository.PollutantRepository;
import com.project.easywork.pollutant.service_data.IPollutantDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollutantDataService implements IPollutantDataService {
  
  private final PollutantRepository pollutantRepository;
  
  @Override
  public Pollutant findById(Long id) {
    return pollutantRepository.findById(id).orElseThrow();
  }
  
  @Override
  public Pollutant save(Pollutant pollutant) {
    return pollutantRepository.save(pollutant);
  }
  
  @Override
  public void deleteById(Long id) {
    pollutantRepository.deleteById(id);
  }
  
  @Override
  public List<Pollutant> findAll() {
    return pollutantRepository.findAll();
  }
}
