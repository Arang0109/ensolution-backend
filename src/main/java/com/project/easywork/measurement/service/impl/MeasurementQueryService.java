package com.project.easywork.measurement.service.impl;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.common.resolver.DomainEntityResolver;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.equipment.service.impl.EquipmentService;
import com.project.easywork.measurement.domain.dto.draft_source.DraftSource;
import com.project.easywork.measurement.domain.dto.draft_source.DraftSourceMaterial;
import com.project.easywork.measurement.service.IMeasurementQueryService;
import com.project.easywork.measurement.service.impl.assembler.DraftSourceAssembler;
import com.project.easywork.plan.domain.dto.PlanCreateBundleD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementQueryService implements IMeasurementQueryService {
  
  private final DraftSourceAssembler draftSourceAssembler;
  
  private final DomainEntityResolver domainEntityResolver;
  
  private final EquipmentService equipmentService;
  
  @Override
  public DraftSource createDraftSource(PlanCreateBundleD command) {
    
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
    
    DraftSourceMaterial draftSourceMaterial = DraftSourceMaterial.builder()
        .referenceNumber(command.getReferenceNumber())
        .measureDate(command.getPlan().getMeasureDate())
        .measurementField(command.getPlan().getMeasurementField())
        .measurementType(command.getPlan().getMeasurementType())
        .stack(stack)
        .measurementItems(measurementItems)
        .team(team)
        .vehicleNumber(command.getVehicleNumber())
        .mentor(command.getMentor())
        .mentee(command.getMentee())
        .particleSampler(particleSampler)
        .gasSampler(gasSampler)
        .pitotTube(pitotTube)
        .nozzle(nozzle)
        .build();
    
    return draftSourceAssembler.assemble(draftSourceMaterial);
  }
}