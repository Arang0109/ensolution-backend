package com.project.easywork.client.service_data;

import com.project.easywork.client.entity.Workplace;

import java.util.List;

public interface WorkplaceDataService {
  Workplace findByWorkplaceId(Long id);
  void saveWorkplace(Workplace workplace);
  void deleteWorkplace(Long workplaceId);
  List<Workplace> findAllWorkplaces();
}