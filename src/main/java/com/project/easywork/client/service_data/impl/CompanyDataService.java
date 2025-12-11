package com.project.easywork.client.service_data.impl;

import com.project.easywork.client.domain.persistance.Company;
import com.project.easywork.client.repository.CompanyRepository;
import com.project.easywork.client.service_data.ICompanyDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyDataService implements ICompanyDataService {
  
  private final CompanyRepository companyRepository;
  
  @Override
  public Company findById(Long companyId) {
    return companyRepository.findById(companyId)
        .orElseThrow();
  }
  
  @Override
  public Company save(Company company) {
    return companyRepository.save(company);
  }
  
  @Override
  public void deleteById(Long companyId) {
    companyRepository.deleteById(companyId);
  }
  
  @Override
  public List<Company> findAll() {
    return companyRepository.findAll();
  }
}
