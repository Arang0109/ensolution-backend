package com.project.easywork.pollutant.service;

import com.project.easywork.pollutant.domain.dto.PollutantCreateRequestDto;
import com.project.easywork.pollutant.domain.dto.PollutantResponseDto;
import com.project.easywork.pollutant.domain.dto.PollutantUpdateRequestDto;

import java.util.List;

public interface IPollutantService {
  PollutantResponseDto registerPollutant(PollutantCreateRequestDto requestDto);
  PollutantResponseDto getPollutant(Long pollutantId);
  List<PollutantResponseDto> getPollutants();
  PollutantResponseDto updatePollutant(Long pollutantId, PollutantUpdateRequestDto requestDto);
  void removePollutant(Long pollutantId);
}
