package com.project.easywork.agency.mapper;

import com.project.easywork.agency.domain.dto.TeamCreateD;
import com.project.easywork.agency.domain.dto.TeamDetailD;
import com.project.easywork.agency.domain.dto.TeamD;
import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.user.mapper.UserMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder,
    uses = {
        UserMapper.class
    })
public interface TeamMapper {
  Team toEntity(TeamCreateD dto);
  
  TeamD toDto(Team team);
  
  @Mapping(source = ".", target = "team")
  TeamDetailD toDetailDto(Team team);
  
  List<TeamD> toDtoList(List<Team> teams);
}