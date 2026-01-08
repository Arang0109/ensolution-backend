package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.facility.FacilityCreateD;
import com.project.easywork.client.domain.dto.facility.FacilityD;
import com.project.easywork.client.domain.dto.facility.FacilityUpdateD;
import com.project.easywork.client.domain.persistance.Facility;
import com.project.easywork.client.domain.persistance.Prevention;
import com.project.easywork.client.mapper.FacilityMapper;
import com.project.easywork.client.service.IFacilityService;
import com.project.easywork.client.service_data.IFacilityDataService;
import com.project.easywork.common.resolver.DomainEntityResolver;
import jakarta.persistence.EntityManager;
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
  private final EntityManager entityManager;
  
  @Override
  public FacilityD registerFacility(FacilityCreateD requestDto) {
    
    Prevention prevention = domainEntityResolver.getPreventionOrThrow(requestDto.getPreventionId());
    Facility facility = facilityMapper.toEntity(requestDto);
    facility.attachPrevention(prevention);
    
    return facilityMapper.toDto(facilityDataService.save(facility));
  }
  
  @Override
  public List<FacilityD> registerFacilities(List<FacilityCreateD> requestDtos, Prevention prevention) {
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
  public List<FacilityD> getFacilities() {
    return facilityMapper.toDtoList(facilityDataService.findAll());
  }
  
  @Override
  public FacilityD updateFacility(Long facilityId, FacilityUpdateD dto) {
    Facility facility = domainEntityResolver.getFacilityOrThrow(facilityId);
    facility.update(dto);
    
    entityManager.flush();
    
    return facilityMapper.toDto(facility);
  }
  
  @Override
  public void removeFacility(Long facilityId) {
    domainEntityResolver.getFacilityOrThrow(facilityId);
    facilityDataService.deleteById(facilityId);
  }
}
