package com.project.easywork.client.service.impl;

import com.project.easywork.client.domain.dto.company.CompanyCreateRequestDto;
import com.project.easywork.client.domain.dto.company.CompanyDetailResponseDto;
import com.project.easywork.client.domain.dto.company.CompanyResponseDto;
import com.project.easywork.client.domain.dto.company.CompanyUpdateRequestDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceResponseDto;
import com.project.easywork.client.domain.persistance.Company;
import com.project.easywork.client.mapper.CompanyMapper;
import com.project.easywork.client.mapper.WorkplaceMapper;
import com.project.easywork.client.service.ICompanyService;
import com.project.easywork.client.service_data.ICompanyDataService;
import com.project.easywork.client.service_data.IWorkplaceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService implements ICompanyService {
  
  private final ICompanyDataService companyDataService;
  private final IWorkplaceDataService workplaceDataService;
  private final CompanyMapper companyMapper;
  private final WorkplaceMapper workplaceMapper;
  
  @Override
  public CompanyResponseDto registerCompany(CompanyCreateRequestDto requestDto) {
    Company company = companyMapper.toEntityFromCompanyCreateDto(requestDto);
    
    return companyMapper.toDto(companyDataService.save(company));
  }
  
  @Override
  @Transactional(readOnly = true)
  public CompanyDetailResponseDto getCompany(Long companyId) {
    CompanyResponseDto company = companyMapper.toDto(companyDataService.findById(companyId));
    List<WorkplaceResponseDto> workplaces = workplaceMapper.toDtoList(workplaceDataService.findWorkplacesByCompanyId(companyId));
    
    return CompanyDetailResponseDto.builder()
        .company(company)
        .workplaces(workplaces)
        .build();
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<CompanyResponseDto> getCompanies() {
    return companyMapper.toDtoList(companyDataService.findAll());
  }
  
  @Override
  public CompanyResponseDto updateCompany(Long companyId, CompanyUpdateRequestDto requestDto) {
    Company company = companyDataService.findById(companyId);
    company.update(requestDto);
    return companyMapper.toDto(company);
  }
  
  @Override
  public void removeCompany(Long companyId) {
    companyDataService.deleteById(companyId);
  }
}
