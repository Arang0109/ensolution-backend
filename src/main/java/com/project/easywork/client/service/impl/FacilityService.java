package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.facility.FacilityCreateRequestDto;
import com.project.easywork.client.domain.dto.facility.FacilityResponseDto;
import com.project.easywork.client.domain.dto.facility.FacilityUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Facility;
import com.project.easywork.client.mapper.FacilityMapper;
import com.project.easywork.client.service.IFacilityService;
import com.project.easywork.client.service_data.IFacilityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FacilityService implements IFacilityService {
  
  private final IFacilityDataService facilityDataService;
  private final FacilityMapper facilityMapper;
  
  @Override
  public void registerFacility(FacilityCreateRequestDto requestDto) {
    Facility facility = facilityMapper.toEntityFromFacilityCreateDto(requestDto);
    facilityDataService.save(facility);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<FacilityResponseDto> getFacilities() {
    return facilityMapper.toDtoList(facilityDataService.findAll());
  }
  
  @Override
  public FacilityResponseDto updateFacility(Long facilityId, FacilityUpdateRequestDto requestDto) {
    Facility facility = facilityDataService.findById(facilityId);
    facility.update(requestDto);
    return facilityMapper.toDto(facility);
  }
  
  @Override
  public void removeFacility(Long facilityId) {
    facilityDataService.deleteById(facilityId);
  }
}
