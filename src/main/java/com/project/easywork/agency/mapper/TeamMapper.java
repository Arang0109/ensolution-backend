package com.project.easywork.agency.mapper;

import com.project.easywork.agency.domain.dto.TeamCreateRequestDto;
import com.project.easywork.agency.domain.dto.TeamResponseDto;
import com.project.easywork.agency.domain.entity.Team;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder)
public interface TeamMapper {
  Team toEntity(TeamCreateRequestDto dto);
  TeamResponseDto toDto(Team team);
  
  List<TeamResponseDto> toDtoList(List<Team> teams);
}