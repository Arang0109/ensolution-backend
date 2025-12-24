package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.Target;

import java.util.List;

public interface ITargetDataService {
  Target findById(Long targetId);
  
  Target save(Target target);
  
  List<Target> saveAll(List<Target> targets);
  
  void deleteById(Long targetId);
  
  List<Target> findAll();
  
  List<Target> findTargetsByPreventionId(Long preventionId);
}
