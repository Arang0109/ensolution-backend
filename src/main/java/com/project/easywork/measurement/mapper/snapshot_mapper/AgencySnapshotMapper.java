package com.project.easywork.measurement.mapper.snapshot_mapper;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.measurement.dto.snapshot.agency.AgencySnapshot;
import org.springframework.stereotype.Component;

@Component
public class AgencySnapshotMapper {
  
  public AgencySnapshot toSnapshot(
      Team team,
      String vehicleNumber,
      String mentor,
      String mentee
  ) {
    return new AgencySnapshot(
        new AgencySnapshot.TeamSnapshot(team.getId(), team.getName()),
        vehicleNumber,
        mentor,
        mentee
    );
  }
}