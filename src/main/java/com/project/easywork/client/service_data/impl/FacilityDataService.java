package com.project.easywork.client.service_data.impl;

import com.project.easywork.client.domain.persistance.Facility;
import com.project.easywork.client.repository.FacilityRepository;
import com.project.easywork.client.service_data.IFacilityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityDataService implements IFacilityDataService {
  
  private final FacilityRepository facilityRepository;
  
  @Override
  public Facility findById(Long facilityId) {
    return facilityRepository.findById(facilityId).orElse(null);
  }
  
  @Override
  public Facility save(Facility facility) {
    return facilityRepository.save(facility);
  }
  
  @Override
  public void saveAll(List<Facility> facilities) {
    facilityRepository.saveAll(facilities);
  }
  
  @Override
  public List<Facility> findAll() {
    return facilityRepository.findAll();
  }
}
