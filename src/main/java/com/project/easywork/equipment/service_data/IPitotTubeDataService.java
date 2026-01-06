package com.project.easywork.equipment.service_data;

import com.project.easywork.equipment.domain.persistance.PitotTube;

import java.util.List;

public interface IPitotTubeDataService {
  PitotTube findById(Long pitotTubeId);
  List<PitotTube> findAll();
}
