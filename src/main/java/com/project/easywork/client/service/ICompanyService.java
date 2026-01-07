package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.company.CompanyCreateD;
import com.project.easywork.client.domain.dto.company.CompanyDetailD;
import com.project.easywork.client.domain.dto.company.CompanyD;
import com.project.easywork.client.domain.dto.company.CompanyUpdateD;

import java.util.List;

public interface ICompanyService {
  CompanyD registerCompany(CompanyCreateD requestDto);
  CompanyDetailD getCompany(Long companyId);
  List<CompanyD> getCompanies();
  CompanyD updateCompany(Long companyId, CompanyUpdateD requestDto);
  void removeCompany(Long companyId);
}