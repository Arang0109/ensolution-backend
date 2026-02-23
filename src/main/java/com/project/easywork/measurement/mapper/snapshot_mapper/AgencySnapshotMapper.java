package com.project.easywork.measurement.mapper.snapshot_mapper;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.measurement.dto.snapshot.agency.AgencySnapshot;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AgencySnapshotMapper {
  
  public AgencySnapshot toSnapshot(
      String referenceNumber,
      LocalDate measureDate,
      String measurementType,
      boolean simplifiedMeasurement,
      Team team,
      String vehicleNumber,
      String mentor,
      String mentee
  ) {
    return new AgencySnapshot(
        referenceNumber,
        measureDate,
        measurementType,
        simplifiedMeasurement,
        new AgencySnapshot.TeamSnapshot(team.getId(), team.getName()),
        vehicleNumber,
        mentor,
        mentee
    );
  }
}