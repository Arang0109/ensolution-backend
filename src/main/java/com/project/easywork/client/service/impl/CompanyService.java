package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.company.CompanyCreateRequestDto;
import com.project.easywork.client.domain.dto.company.CompanyDetailResponseDto;
import com.project.easywork.client.domain.dto.company.CompanyResponseDto;
import com.project.easywork.client.domain.dto.company.CompanyUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Company;
import com.project.easywork.client.mapper.CompanyMapper;
import com.project.easywork.client.service.ICompanyService;
import com.project.easywork.client.service_data.ICompanyDataService;
import com.project.easywork.client.validator.CompanyValidator;
import com.project.easywork.common.resolver.DomainEntityResolver;
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
  
  @Override
  @Transactional
  public CompanyResponseDto registerCompany(CompanyCreateRequestDto dto) {
    // Validation step
    companyValidator.validateForCreate(dto);
    
    Company company = companyMapper.toEntity(dto);
    return companyMapper.toDto(companyDataService.save(company));
  }
  
  @Override
  @Transactional(readOnly = true)
  public CompanyDetailResponseDto getCompany(Long companyId) {
    Company company = domainEntityResolver.getCompanyOrThrow(companyId);
    return companyMapper.toDetailDto(company);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<CompanyResponseDto> getCompanies() {
    return companyMapper.toDtoList(companyDataService.findAll());
  }
  
  @Override
  @Transactional
  public CompanyResponseDto updateCompany(Long companyId, CompanyUpdateRequestDto dto) {
    // Validation step
    companyValidator.validateForUpdate(companyId, dto);
    
    Company company = domainEntityResolver.getCompanyOrThrow(companyId);
    companyMapper.updateCompany(dto, company);
    return companyMapper.toDto(company);
  }
  
  @Override
  @Transactional
  public void removeCompany(Long companyId) {
    domainEntityResolver.getCompanyOrThrow(companyId);
    companyDataService.deleteById(companyId);
  }
}