package com.project.easywork.pollutant.service_data;

import com.project.easywork.pollutant.domain.persistance.Pollutant;

import java.util.List;

public interface IPollutantDataService {
  Pollutant findById(Long id);
  Pollutant save(Pollutant pollutant);
  void deleteById(Long id);
  List<Pollutant> findAll();
}
