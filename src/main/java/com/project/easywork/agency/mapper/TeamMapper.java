package com.project.easywork.agency.mapper;

import com.project.easywork.agency.domain.dto.TeamCreateD;
import com.project.easywork.agency.domain.dto.TeamD;
import com.project.easywork.agency.domain.dto.TeamUpdateCommand;
import com.project.easywork.agency.domain.dto.TeamUpdateD;
import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.common.util.MapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
  Team toEntity(TeamCreateD dto);
  TeamD toDto(Team team);
  
  @Mapping(target = "name", qualifiedByName = "normalize")
  @Mapping(target = "vehicleNumber", qualifiedByName = "normalize")
  @Mapping(target = "mentor", qualifiedByName = "normalize")
  @Mapping(target = "mentee", qualifiedByName = "normalize")
  TeamUpdateCommand toUpdateCommand(TeamUpdateD dto);
  
  List<TeamD> toDtoList(List<Team> teams);
  
  @Named("normalize")
  default String normalize(String value) {
    return MapperUtils.normalize(value);
  }
}