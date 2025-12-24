package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.facility.FacilityCreateRequestDto;
import com.project.easywork.client.domain.dto.facility.FacilityResponseDto;
import com.project.easywork.client.domain.dto.facility.FacilityUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Facility;
import com.project.easywork.client.domain.persistance.Prevention;
import com.project.easywork.client.mapper.FacilityMapper;
import com.project.easywork.client.service.IFacilityService;
import com.project.easywork.client.service_data.IFacilityDataService;
import com.project.easywork.common.resolver.DomainEntityResolver;
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
  
  private final DomainEntityResolver domainEntityResolver;
  
  @Override
  public FacilityResponseDto registerFacility(FacilityCreateRequestDto requestDto) {
    
    Prevention prevention = domainEntityResolver.getPreventionOrThrow(requestDto.getPreventionId());
    Facility facility = facilityMapper.toEntity(requestDto);
    facility.attachPrevention(prevention);
    
    return facilityMapper.toDto(facilityDataService.save(facility));
  }
  
  @Override
  public List<FacilityResponseDto> registerFacilities(List<FacilityCreateRequestDto> requestDtos, Prevention prevention) {
    if (requestDtos == null || requestDtos.isEmpty()) {
      return List.of();
    }
    
    List<Facility> facilities = requestDtos.stream()
        .map(dto -> {
          Facility facility = facilityMapper.toEntity(dto);
          facility.attachPrevention(prevention);
          return facility;
        })
        .toList();
    
    return facilityDataService.saveAll(facilities).stream()
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
    Facility facility = domainEntityResolver.getFacilityOrThrow(facilityId);
    facilityMapper.updateFacility(requestDto, facility);
    return facilityMapper.toDto(facility);
  }
  
  @Override
  public void removeFacility(Long facilityId) {
    domainEntityResolver.getFacilityOrThrow(facilityId);
    facilityDataService.deleteById(facilityId);
  }
}
