package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.company.CompanyCreateRequestDto;
import com.project.easywork.client.domain.dto.company.CompanyDetailResponseDto;
import com.project.easywork.client.domain.dto.company.CompanyResponseDto;
import com.project.easywork.client.domain.dto.company.CompanyUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Company;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = WorkplaceMapper.class
)
public interface CompanyMapper {
  
  Company toEntity(CompanyCreateRequestDto dto);
  CompanyResponseDto toDto(Company company);
  
  @Mapping(source = ".", target = "company")
  CompanyDetailResponseDto toDetailDto(Company company);
  
  List<CompanyResponseDto> toDtoList(List<Company> companies);
  
  @BeanMapping(
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  void updateCompany(CompanyUpdateRequestDto dto, @MappingTarget Company company);
}