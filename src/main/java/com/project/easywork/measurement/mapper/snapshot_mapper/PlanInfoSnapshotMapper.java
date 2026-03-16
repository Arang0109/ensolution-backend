package com.project.easywork.measurement.mapper.snapshot_mapper;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.measurement.dto.snapshot.plan_info.PlanInfoSnapshot;
import com.project.easywork.plan.domain.MeasurementField;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PlanInfoSnapshotMapper {
  
  public PlanInfoSnapshot toSnapshot(
      String referenceNumber,
      LocalDate measureDate,
      MeasurementField measurementField,
      String measurementType,
      Team team,
      String vehicleNumber,
      String mentor,
      String mentee
  ) {
    return new PlanInfoSnapshot(
        referenceNumber,
        measureDate,
        measurementField,
        measurementType,
        new PlanInfoSnapshot.TeamSnapshot(team.getId(), team.getName()),
        vehicleNumber,
        mentor,
        mentee
    );
  }
}