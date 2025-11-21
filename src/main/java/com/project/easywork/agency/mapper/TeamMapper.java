package com.project.easywork.agency.mapper;

import com.project.easywork.agency.dto.TeamDto;
import com.project.easywork.agency.entity.Team;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder)
public interface TeamMapper {
  Team toEntity(TeamDto teamDto);
  TeamDto toDto(Team team);
  
  List<TeamDto> toDtoList(List<Team> teams);
}