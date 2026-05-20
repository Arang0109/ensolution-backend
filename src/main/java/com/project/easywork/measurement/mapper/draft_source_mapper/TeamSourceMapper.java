package com.project.easywork.measurement.mapper.draft_source_mapper;

import com.project.easywork.measurement.domain.dto.draft_source.team.TeamSource;
import org.springframework.stereotype.Component;

@Component
public class TeamSourceMapper {
  
  public TeamSource toSource(
      Long teamId,
      String name,
      String vehicleNumber,
      String mentor,
      String mentee
  ) {
    return new TeamSource(
        teamId,
        name,
        vehicleNumber,
        mentor,
        mentee
    );
  }
}