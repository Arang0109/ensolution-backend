package com.project.easywork.client.mapper;

import com.project.easywork.client.domain.dto.workplace.WorkplaceCreateD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceDetailD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceD;
import com.project.easywork.client.domain.persistance.Workplace;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = StackMapper.class
)
public interface WorkplaceMapper {
  
  Workplace toEntity(WorkplaceCreateD dto);
  
  @Mapping(source = "company.id", target = "companyId")
  WorkplaceD toDto(Workplace workplace);
  
  @Mapping(source = ".", target = "workplace")
  WorkplaceDetailD toDetailDto(Workplace workplace);
  
  List<WorkplaceD> toDtoList(List<Workplace> workplaces);
}