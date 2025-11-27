package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.Company;

import java.util.List;

public interface ICompanyDataService {
  Company findById(Long companyId);
  void save(Company company);
  void deleteById(Long companyId);
  List<Company> findAll();
}