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
    return facilityRepository.findById(facilityId)
        .orElseThrow();
  }
  
  @Override
  public Facility save(Facility facility) {
    return facilityRepository.save(facility);
  }
  
  @Override
  public List<Facility> saveAll(List<Facility> facilities) {
    return facilityRepository.saveAll(facilities);
  }
  
  @Override
  public void deleteById(Long facilityId) {
    facilityRepository.deleteById(facilityId);
  }
  
  @Override
  public List<Facility> findAll() {
    return facilityRepository.findAll();
  }
  
  @Override
  public List<Facility> findFacilitiesByPreventionId(Long preventionId) {
    return facilityRepository.findFacilitiesByPreventionId(preventionId);
  }
}
