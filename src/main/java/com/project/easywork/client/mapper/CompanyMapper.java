package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.company.CompanyCreateD;
import com.project.easywork.client.domain.dto.company.CompanyDetailD;
import com.project.easywork.client.domain.dto.company.CompanyD;
import com.project.easywork.client.domain.dto.company.CompanyUpdateD;
import com.project.easywork.client.domain.persistance.Company;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = WorkplaceMapper.class
)
public interface CompanyMapper {
  
  Company toEntity(CompanyCreateD dto);
  CompanyD toDto(Company company);
  
  @Mapping(source = ".", target = "company")
  CompanyDetailD toDetailDto(Company company);
  
  List<CompanyD> toDtoList(List<Company> companies);
}