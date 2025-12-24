package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.workplace.WorkplaceCreateRequestDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceDetailResponseDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceResponseDto;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Workplace;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = StackMapper.class
)
public interface WorkplaceMapper {
  
  Workplace toEntity(WorkplaceCreateRequestDto dto);
  
  @Mapping(source = "company.id", target = "companyId")
  WorkplaceResponseDto toDto(Workplace workplace);
  
  @Mapping(source = ".", target = "workplace")
  WorkplaceDetailResponseDto toDetailDto(Workplace workplace);
  
  List<WorkplaceResponseDto> toDtoList(List<Workplace> workplaces);
  
  @BeanMapping(
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  void updateWorkplace(
      WorkplaceUpdateRequestDto dto,
      @MappingTarget Workplace workplace
  );
}