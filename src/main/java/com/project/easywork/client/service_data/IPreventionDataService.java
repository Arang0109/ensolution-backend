package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.Prevention;

import java.util.List;

public interface IPreventionDataService {
  Prevention findById(Long preventionId);
  
  Prevention save(Prevention prevention);
  
  void deleteById(Long preventionId);
  
  List<Prevention> findAll();
  
  List<Prevention> findPreventionsByStackId(Long stackId);
}
