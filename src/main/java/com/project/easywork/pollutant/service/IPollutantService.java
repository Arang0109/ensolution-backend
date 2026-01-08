package com.project.easywork.pollutant.service;

import com.project.easywork.pollutant.domain.dto.PollutantCreateD;
import com.project.easywork.pollutant.domain.dto.PollutantD;
import com.project.easywork.pollutant.domain.dto.PollutantUpdateD;

import java.util.List;

public interface IPollutantService {
  PollutantD registerPollutant(PollutantCreateD requestDto);
  PollutantD getPollutant(Long pollutantId);
  List<PollutantD> getPollutants();
  PollutantD updatePollutant(Long pollutantId, PollutantUpdateD requestDto);
  void removePollutant(Long pollutantId);
}
