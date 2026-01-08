package com.project.easywork.pollutant.service.impl;

import com.project.easywork.common.resolver.DomainEntityResolver;
import com.project.easywork.pollutant.domain.dto.PollutantCreateD;
import com.project.easywork.pollutant.domain.dto.PollutantD;
import com.project.easywork.pollutant.domain.dto.PollutantUpdateD;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import com.project.easywork.pollutant.mapper.PollutantMapper;
import com.project.easywork.pollutant.service.IPollutantService;
import com.project.easywork.pollutant.service_data.IPollutantDataService;
import com.project.easywork.pollutant.validator.PollutantValidator;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PollutantService implements IPollutantService {
  
  private final IPollutantDataService pollutantDataService;
  private final PollutantMapper pollutantMapper;
  
  private final PollutantValidator pollutantValidator;
  private final DomainEntityResolver domainEntityResolver;
  
  private final EntityManager entityManager;
  
  @Override
  public PollutantD registerPollutant(PollutantCreateD dto) {
    pollutantValidator.validate(dto);
    Pollutant pollutant = pollutantMapper.toEntity(dto);
    return pollutantMapper.toDto(pollutantDataService.save(pollutant));
  }
  
  @Override
  public PollutantD getPollutant(Long pollutantId) {
    Pollutant pollutant = domainEntityResolver.getPollutantOrThrow(pollutantId);
    return pollutantMapper.toDto(pollutant);
  }
  
  @Override
  public List<PollutantD> getPollutants() {
    return pollutantMapper.toDtoList(pollutantDataService.findAll());
  }
  
  @Override
  @PreAuthorize("hasRole('LAB')")
  public PollutantD updatePollutant(Long pollutantId, PollutantUpdateD dto) {
    Pollutant pollutant = domainEntityResolver.getPollutantOrThrow(pollutantId);
    pollutant.update(dto);
    
    entityManager.flush();
    
    return pollutantMapper.toDto(pollutant);
  }
  
  @Override
  @PreAuthorize("hasRole('LAB')")
  public void removePollutant(Long pollutantId) {
    domainEntityResolver.getPollutantOrThrow(pollutantId);
    pollutantDataService.deleteById(pollutantId);
  }
}
