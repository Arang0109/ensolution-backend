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
  
  @Override
  public void registerFacilities(List<FacilityCreateD> dtos, Prevention prevention) {
    if (dtos == null || dtos.isEmpty()) {
      return;
    }
    
    List<Facility> facilities = dtos.stream()
        .map(dto -> {
          Facility facility = facilityMapper.toEntity(dto);
          facility.attachPrevention(prevention);
          return facility;
        })
        .toList();
    
    facilityDataService.saveAll(facilities);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<FacilityD> getFacilities() {
    return facilityMapper.toDtoList(facilityDataService.findAll());
  }
  
  @Override
  public void removeFacility(Long preventionId, Long facilityId) {
    Prevention prevention = domainEntityResolver.getPreventionOrThrow(preventionId);
    Facility facility = domainEntityResolver.getFacilityOrThrow(facilityId);
    prevention.removeFacility(facility);
  }
}
