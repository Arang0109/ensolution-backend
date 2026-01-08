package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.Facility;

import java.util.List;

public interface IFacilityDataService {
  Facility findById(Long facilityId);
  
  Facility save(Facility facility);
  
  void saveAll(List<Facility> facilities);
  
  List<Facility> findAll();
}
