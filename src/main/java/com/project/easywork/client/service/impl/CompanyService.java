package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.company.CompanyCreateD;
import com.project.easywork.client.domain.dto.company.CompanyDetailD;
import com.project.easywork.client.domain.dto.company.CompanyD;
import com.project.easywork.client.domain.dto.company.CompanyUpdateD;
import com.project.easywork.client.domain.persistance.Company;
import com.project.easywork.client.mapper.CompanyMapper;
import com.project.easywork.client.service.ICompanyService;
import com.project.easywork.client.service_data.ICompanyDataService;
import com.project.easywork.client.validator.CompanyValidator;
import com.project.easywork.common.resolver.DomainEntityResolver;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService implements ICompanyService {
  
  private final ICompanyDataService companyDataService;
  private final CompanyMapper companyMapper;
  
  private final CompanyValidator companyValidator;
  private final DomainEntityResolver domainEntityResolver;
  
  private final EntityManager entityManager;
  
  @Override
  @Transactional
  public CompanyD registerCompany(CompanyCreateD dto) {
    companyValidator.validateForCreate(dto);
    
    Company company = companyMapper.toEntity(dto);
    return companyMapper.toDto(companyDataService.save(company));
  }
  
  @Override
  @Transactional(readOnly = true)
  public CompanyDetailD getCompany(Long companyId) {
    Company company = domainEntityResolver.getCompanyOrThrow(companyId);
    return companyMapper.toDetailDto(company);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<CompanyD> getCompanies() {
    return companyMapper.toDtoList(companyDataService.findAll());
  }
  
  @Override
  @Transactional
  public CompanyD updateCompany(Long companyId, CompanyUpdateD dto) {
    // Validation step
    companyValidator.validateForUpdate(companyId, dto);
    Company company = domainEntityResolver.getCompanyOrThrow(companyId);
    company.update(dto);
    
    entityManager.flush();
    
    return companyMapper.toDto(company);
  }
  
  @Override
  @Transactional
  public void removeCompany(Long companyId) {
    domainEntityResolver.getCompanyOrThrow(companyId);
    companyDataService.deleteById(companyId);
  }
}