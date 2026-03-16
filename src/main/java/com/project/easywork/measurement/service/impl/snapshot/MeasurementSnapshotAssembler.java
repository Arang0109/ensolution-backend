package com.project.easywork.measurement.service.impl.snapshot;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.measurement.dto.snapshot.DraftSnapshot;
import com.project.easywork.measurement.mapper.snapshot_mapper.*;
import com.project.easywork.plan.domain.MeasurementField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MeasurementSnapshotAssembler {
  
  private final PlanInfoSnapshotMapper planInfoMapper;
  private final ClientSnapshotMapper clientMapper;
  private final StackMeasurementSnapshotMapper stackMeasurementMapper;
  private final MeasurementEquipmentSnapshotMapper equipmentMapper;
  
  public DraftSnapshot assemble(
      String referenceNumber,
      LocalDate measureDate,
      MeasurementField measurementField,
      String measurementType,
      Stack stack,
      List<StackMeasurement> measurements,
      Team team,
      String vehicleNumber,
      String mentor,
      String mentee,
      EquipmentDoc particleSampler,
      EquipmentDoc gasSampler,
      EquipmentDoc pitotTube,
      EquipmentDoc nozzle
  ) {
    
    return new DraftSnapshot(
        planInfoMapper.toSnapshot(referenceNumber, measureDate, measurementField, measurementType, team, vehicleNumber, mentor, mentee),
        clientMapper.toSnapshot(stack),
        stackMeasurementMapper.toSnapshots(measurements),
        equipmentMapper.toSnapshot(particleSampler, gasSampler, pitotTube, nozzle)
    );
  }
}