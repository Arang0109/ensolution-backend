package com.project.easywork.pollutant.service.impl;

import com.project.easywork.pollutant.domain.dto.PollutantCreateRequestDto;
import com.project.easywork.pollutant.domain.dto.PollutantResponseDto;
import com.project.easywork.pollutant.domain.dto.PollutantUpdateRequestDto;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import com.project.easywork.pollutant.mapper.PollutantMapper;
import com.project.easywork.pollutant.service.IPollutantService;
import com.project.easywork.pollutant.service_data.IPollutantDataService;
import com.project.easywork.pollutant.validator.PollutantValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PollutantService implements IPollutantService {
  
  private final PollutantValidator pollutantValidator;
  private final IPollutantDataService pollutantDataService;
  private final PollutantMapper pollutantMapper;
  
  @Override
  public PollutantResponseDto registerPollutant(PollutantCreateRequestDto requestDto) {
    pollutantValidator.validate(requestDto);
    Pollutant pollutant = pollutantMapper.toEntityFromPollutantCreateDto(requestDto);
    return pollutantMapper.toDto(pollutantDataService.save(pollutant));
  }
  
  @Override
  public PollutantResponseDto getPollutant(Long pollutantId) {
    return pollutantMapper.toDto(
        pollutantDataService.findById(pollutantId)
    );
  }
  
  @Override
  public List<PollutantResponseDto> getPollutants() {
    return pollutantMapper.toDtoList(pollutantDataService.findAll());
  }
  
  @Override
  @PreAuthorize("hasRole('LAB')")
  public PollutantResponseDto updatePollutant(Long pollutantId, PollutantUpdateRequestDto requestDto) {
    Pollutant pollutant = pollutantDataService.findById(pollutantId);
    pollutant.update(requestDto);
    return pollutantMapper.toDto(pollutant);
  }
  
  @Override
  @PreAuthorize("hasRole('LAB')")
  public void removePollutant(Long pollutantId) {
    pollutantDataService.deleteById(pollutantId);
  }
}
