package com.project.easywork.measurement.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.agency.service_data.impl.TeamDataService;
import com.project.easywork.client.domain.persistance.*;
import com.project.easywork.client.service_data.impl.*;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.service.impl.EquipmentService;
import com.project.easywork.measurement.dto.snapshot.MeasurementSnapshot;
import com.project.easywork.measurement.service.IMeasurementQueryService;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementQueryService implements IMeasurementQueryService {
  
  private final StackDataService stackDataService;
  private final StackMeasurementDataService stackMeasurementDataService;
  private final TeamDataService teamDataService;
  private final EquipmentService equipmentService;
  private final MeasurementSnapshotAssembler snapshotAssembler;
  
  @Override
  public MeasurementSnapshot loadSnapshot(PlanCreateBundleD dto) {
    
    Stack stack = stackDataService.findById(dto.getPlan().getStackId());
    
    List<StackMeasurement> measurements =
        stackMeasurementDataService.findByIdIn(dto.getPlan().getMeasurementIds());
    
    Team team = teamDataService.findById(dto.getPlan().getTeamId());
    
    EquipmentDoc particleSampler =
        equipmentService.getEquipment(dto.getParticleSamplerId());
    
    EquipmentDoc gasSampler =
        equipmentService.getEquipment(dto.getGasSamplerId());
    
    EquipmentDoc pitotTube =
        equipmentService.getEquipment(dto.getPitotTubeId());
    
    EquipmentDoc nozzle =
        equipmentService.getEquipment(dto.getNozzleId());
    
    return snapshotAssembler.assemble(
        dto.getPlan().getMeasureDate(),
        dto.getPlan().getMeasurementType(),
        dto.isSimplifiedMeasurement(),
        stack,
        measurements,
        team,
        dto.getVehicleNumber(),
        dto.getMentor(),
        dto.getMentee(),
        particleSampler,
        gasSampler,
        pitotTube,
        nozzle
    );
  }
}