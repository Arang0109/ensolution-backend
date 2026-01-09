package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.facility.FacilityCreateD;
import com.project.easywork.client.domain.dto.facility.FacilityD;
import com.project.easywork.client.domain.persistance.Prevention;

import java.util.List;

public interface IFacilityService {
  void registerFacilities(List<FacilityCreateD> requestDtos, Prevention prevention);
  
  List<FacilityD> getFacilities();
  
  void removeFacility(Long preventionId, Long facilityId);
}
