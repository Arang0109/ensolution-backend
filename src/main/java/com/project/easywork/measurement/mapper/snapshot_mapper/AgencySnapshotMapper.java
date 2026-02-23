package com.project.easywork.measurement.mapper.snapshot_mapper;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.measurement.dto.snapshot.agency.AgencySnapshot;
import com.project.easywork.plan.domain.MeasurementField;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AgencySnapshotMapper {
  
  public AgencySnapshot toSnapshot(
      String referenceNumber,
      LocalDate measureDate,
      MeasurementField measurementField,
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
        measurementField,
        measurementType,
        simplifiedMeasurement,
        new AgencySnapshot.TeamSnapshot(team.getId(), team.getName()),
        vehicleNumber,
        mentor,
        mentee
    );
  }
}