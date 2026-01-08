package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.facility.FacilityCreateD;
import com.project.easywork.client.domain.dto.facility.FacilityD;
import com.project.easywork.client.domain.dto.facility.FacilityUpdateD;
import com.project.easywork.client.domain.persistance.Prevention;

import java.util.List;

public interface IFacilityService {
  FacilityD registerFacility(FacilityCreateD requestDto);
  
  List<FacilityD> registerFacilities(List<FacilityCreateD> requestDtos, Prevention prevention);
  
  List<FacilityD> getFacilities();
  
  FacilityD updateFacility(Long facilityId, FacilityUpdateD requestDto);
  
  void removeFacility(Long facilityId);
}
