package com.project.easywork.agency.mapper;

import com.project.easywork.agency.domain.dto.TeamCreateD;
import com.project.easywork.agency.domain.dto.TeamD;
import com.project.easywork.agency.domain.entity.Team;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder
)
public interface TeamMapper {
  Team toEntity(TeamCreateD dto);
  TeamD toDto(Team team);
  
  List<TeamD> toDtoList(List<Team> teams);
}