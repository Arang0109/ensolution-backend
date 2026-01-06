package com.project.easywork.agency.mapper;

import com.project.easywork.agency.domain.dto.TeamCreateRequestDto;
import com.project.easywork.agency.domain.dto.TeamDetailResponseDto;
import com.project.easywork.agency.domain.dto.TeamResponseDto;
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
        UserMapper.class,
        VehicleMapper.class
    })
public interface TeamMapper {
  Team toEntity(TeamCreateRequestDto dto);
  
  @Mapping(source = "particularEquip.id", target = "particularEquipId")
  @Mapping(source = "pitotTube.id", target = "pitotTubeId")
  TeamResponseDto toDto(Team team);
  
  @Mapping(source = ".", target = "team")
  TeamDetailResponseDto toDetailDto(Team team);
  
  List<TeamResponseDto> toDtoList(List<Team> teams);
}