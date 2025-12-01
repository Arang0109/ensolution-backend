package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.company.CompanyCreateRequestDto;
import com.project.easywork.client.domain.dto.company.CompanyResponseDto;
import com.project.easywork.client.domain.persistance.Company;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder())
public interface CompanyMapper {
  
  Company toEntityFromCompanyCreateDto(CompanyCreateRequestDto dto);
  CompanyResponseDto toDto(Company company);
  
  List<CompanyResponseDto> toDtoList(List<Company> companies);
}