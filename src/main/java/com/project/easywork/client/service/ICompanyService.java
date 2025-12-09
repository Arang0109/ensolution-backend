package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.company.CompanyCreateRequestDto;
import com.project.easywork.client.domain.dto.company.CompanyDetailResponseDto;
import com.project.easywork.client.domain.dto.company.CompanyResponseDto;
import com.project.easywork.client.domain.dto.company.CompanyUpdateRequestDto;

import java.util.List;

public interface ICompanyService {
  CompanyResponseDto registerCompany(CompanyCreateRequestDto requestDto);
  CompanyDetailResponseDto getCompany(Long companyId);
  List<CompanyResponseDto> getCompanies();
  CompanyResponseDto updateCompany(Long companyId, CompanyUpdateRequestDto requestDto);
  void removeCompany(Long companyId);
}