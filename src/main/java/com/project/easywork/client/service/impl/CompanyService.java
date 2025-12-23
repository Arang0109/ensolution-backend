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
import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService implements ICompanyService {
  
  private final ICompanyDataService companyDataService;
  private final CompanyMapper companyMapper;
  private final CompanyValidator companyValidator;
  
  @Override
  @Transactional
  public CompanyResponseDto registerCompany(CompanyCreateRequestDto requestDto) {
    companyValidator.validateForCreate(requestDto);
    Company company = companyMapper.toEntity(requestDto);
    
    try {
      return companyMapper.toDto(companyDataService.save(company));
    } catch (DataIntegrityViolationException e) {
      throw new CustomException(ErrorCode.CONFLICT);
    }
  }
  
  @Override
  @Transactional(readOnly = true)
  public CompanyDetailResponseDto getCompany(Long companyId) {
    Company company = getCompanyOrThrow(companyId);
    return companyMapper.toDetailDto(company);
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<CompanyResponseDto> getCompanies() {
    return companyMapper.toDtoList(companyDataService.findAll());
  }
  
  @Override
  @Transactional
  public CompanyResponseDto updateCompany(Long companyId, CompanyUpdateRequestDto requestDto) {
    
    companyValidator.validateForUpdate(companyId, requestDto);
    
    Company company = companyDataService.findById(companyId);
    companyMapper.updateCompany(requestDto, company);
    
    try {
      companyDataService.save(company);
    } catch (DataIntegrityViolationException e) {
      throw new CustomException(ErrorCode.CONFLICT);
    }
    
    return companyMapper.toDto(company);
  }
  
  @Override
  @Transactional
  public void removeCompany(Long companyId) {
    getCompanyOrThrow(companyId);
    companyDataService.deleteById(companyId);
  }
  
  private Company getCompanyOrThrow(Long companyId) {
    Company company = companyDataService.findById(companyId);
    if (company == null) {
      throw new CustomException(ErrorCode.NOT_FOUND, "해당 의뢰업체를 찾을 수 없습니다.");
    }
    return company;
  }
}
