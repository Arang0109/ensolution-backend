package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.Company;

import java.util.List;

public interface ICompanyDataService {
  Company findById(Long id);
  Company save(Company entity);
  void deleteById(Long id);
  List<Company> findAll();
}