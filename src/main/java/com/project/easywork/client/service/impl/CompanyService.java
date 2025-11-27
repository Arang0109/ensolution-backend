package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.company.CompanyCreateRequestDto;
import com.project.easywork.client.domain.dto.company.CompanyResponseDto;
import com.project.easywork.client.domain.dto.company.CompanyUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Company;
import com.project.easywork.client.mapper.CompanyMapper;
import com.project.easywork.client.service.ICompanyService;
import com.project.easywork.client.service_data.ICompanyDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService implements ICompanyService {
  
  private final ICompanyDataService companyDataService;
  private final CompanyMapper companyMapper;
  
  @Override
  public void registerCompany(CompanyCreateRequestDto requestDto) {
    companyDataService.saveCompany(companyMapper.toEntityFromCompanyCreateDto(requestDto));
  }
  
  @Override
  @Transactional(readOnly = true)
  public CompanyResponseDto getCompany(Long companyId) {
    return companyMapper.toDto(companyDataService.findByCompanyId(companyId));
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<CompanyResponseDto> getCompanies() {
    return companyMapper.toDtoList(companyDataService.findAllCompanies());
  }
  
  @Override
  public CompanyResponseDto updateCompany(Long companyId, CompanyUpdateRequestDto requestDto) {
    Company company = companyDataService.findByCompanyId(companyId);
    company.update(requestDto);
    return companyMapper.toDto(company);
  }
  
  @Override
  public void removeCompany(Long companyId) {
    companyDataService.deleteCompany(companyId);
  }
}
