package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.Workplace;

import java.util.List;

public interface IWorkplaceDataService {
  Workplace findById(Long id);
  Workplace save(Workplace workplace);
  void deleteById(Long workplaceId);
  List<Workplace> findAll();
  List<Workplace> findWorkplacesByCompanyId(Long CompanyId);
}