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
  public FacilityResponseDto registerFacility(FacilityCreateRequestDto requestDto) {
    Facility facility = facilityMapper.toEntityFromFacilityCreateDto(requestDto);
    return facilityMapper.toDto(facilityDataService.save(facility));
  }
  
  @Override
  public List<FacilityResponseDto> registerFacilities(List<FacilityCreateRequestDto> requestDtos) {
    if (requestDtos == null || requestDtos.isEmpty()) {
      return List.of();
    }
    
    List<Facility> facilities = requestDtos.stream()
        .map(dto -> {
          Facility facility = facilityMapper.toEntityFromFacilityCreateDto(dto);
          return facilityDataService.save(facility);
        })
        .toList();
    
    return facilities.stream()
        .map(facilityMapper::toDto)
        .toList();
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
