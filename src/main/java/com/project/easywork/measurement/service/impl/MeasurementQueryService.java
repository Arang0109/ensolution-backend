package com.project.easywork.measurement.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.*;
import com.project.easywork.common.resolver.DomainEntityResolver;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.service.impl.EquipmentService;
import com.project.easywork.measurement.dto.snapshot.DraftSnapshot;
import com.project.easywork.measurement.service.IMeasurementQueryService;
import com.project.easywork.measurement.service.impl.snapshot.MeasurementSnapshotAssembler;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementQueryService implements IMeasurementQueryService {
  
  private final MeasurementSnapshotAssembler snapshotAssembler;
  
  private final DomainEntityResolver domainEntityResolver;
  
  private final EquipmentService equipmentService;
  
  @Override
  public DraftSnapshot createSnapshot(PlanCreateBundleD command) {
    
    Stack stack = domainEntityResolver.getStackOrThrow(command.getPlan().getStackId());
    
    List<StackMeasurement> measurementItems = domainEntityResolver.getStackMeasurementsOrThrow(command.getPlan().getMeasurementItemIds());
    
    Team team = domainEntityResolver.getTeamOrThrow(command.getPlan().getTeamId());
    
    EquipmentDoc particleSampler =
        equipmentService.getEquipment(command.getParticleSamplerId());
    
    EquipmentDoc gasSampler =
        equipmentService.getEquipment(command.getGasSamplerId());
    
    EquipmentDoc pitotTube =
        equipmentService.getEquipment(command.getPitotTubeId());
    
    EquipmentDoc nozzle =
        equipmentService.getEquipment(command.getNozzleId());
    
    return snapshotAssembler.assemble(
        command.getReferenceNumber(),
        command.getPlan().getMeasureDate(),
        command.getPlan().getMeasurementField(),
        command.getPlan().getMeasurementType(),
        stack,
        measurementItems,
        team,
        command.getVehicleNumber(),
        command.getMentor(),
        command.getMentee(),
        particleSampler,
        gasSampler,
        pitotTube,
        nozzle
    );
  }
}