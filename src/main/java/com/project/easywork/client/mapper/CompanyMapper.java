package com.project.easywork.client.mapper;

import com.project.easywork.client.dto.CompanyDto;
import com.project.easywork.client.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
  
  Company toEntity(CompanyDto dto);
  
  CompanyDto toDto(Company company);
}