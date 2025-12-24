package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.facility.FacilityCreateRequestDto;
import com.project.easywork.client.domain.dto.facility.FacilityResponseDto;
import com.project.easywork.client.domain.dto.facility.FacilityUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Prevention;

import java.util.List;

public interface IFacilityService {
  FacilityResponseDto registerFacility(FacilityCreateRequestDto requestDto);
  
  List<FacilityResponseDto> registerFacilities(List<FacilityCreateRequestDto> requestDtos, Prevention prevention);
  
  List<FacilityResponseDto> getFacilities();
  
  FacilityResponseDto updateFacility(Long facilityId, FacilityUpdateRequestDto requestDto);
  
  void removeFacility(Long facilityId);
}
