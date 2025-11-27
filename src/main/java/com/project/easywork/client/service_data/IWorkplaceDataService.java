package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.Workplace;

import java.util.List;

public interface IWorkplaceDataService {
  Workplace findById(Long id);
  void saveWorkplace(Workplace workplace);
  void deleteWorkplace(Long workplaceId);
  List<Workplace> findAllWorkplaces();
  List<Workplace> findWorkplacesByCompanyId(Long CompanyId);
}