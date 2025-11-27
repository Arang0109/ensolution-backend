package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.company.CompanyCreateRequestDto;
import com.project.easywork.client.domain.dto.company.CompanyResponseDto;
import com.project.easywork.client.domain.dto.company.CompanyUpdateRequestDto;

import java.util.List;

public interface ICompanyService {
  void registerCompany(CompanyCreateRequestDto requestDto);
  CompanyResponseDto getCompany(Long companyId);
  List<CompanyResponseDto> getCompanies();
  CompanyResponseDto updateCompany(Long companyId, CompanyUpdateRequestDto requestDto);
  void removeCompany(Long companyId);
}