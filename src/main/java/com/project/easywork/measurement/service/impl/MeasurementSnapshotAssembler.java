package com.project.easywork.measurement.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.measurement.dto.snapshot.MeasurementSnapshot;
import com.project.easywork.measurement.mapper.snapshot_mapper.AgencySnapshotMapper;
import com.project.easywork.measurement.mapper.snapshot_mapper.ClientSnapshotMapper;
import com.project.easywork.measurement.mapper.snapshot_mapper.MeasurementEquipmentSnapshotMapper;
import com.project.easywork.measurement.mapper.snapshot_mapper.StackMeasurementSnapshotMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeasurementSnapshotAssembler {
  
  private final AgencySnapshotMapper agencyMapper;
  private final ClientSnapshotMapper clientMapper;
  private final StackMeasurementSnapshotMapper stackMeasurementMapper;
  private final MeasurementEquipmentSnapshotMapper equipmentMapper;
  
  public MeasurementSnapshot assemble(
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
    
    return new MeasurementSnapshot(
        agencyMapper.toSnapshot(team, vehicleNumber, mentor, mentee),
        clientMapper.toSnapshot(stack),
        stackMeasurementMapper.toSnapshots(measurements),
        equipmentMapper.toSnapshot(particleSampler, gasSampler, pitotTube, nozzle)
    );
  }
}