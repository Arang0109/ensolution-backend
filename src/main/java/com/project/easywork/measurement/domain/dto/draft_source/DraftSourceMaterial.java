package com.project.easywork.measurement.domain.dto.draft_source;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.Stack;
import com.project.easywork.client.domain.persistance.StackMeasurement;
import com.project.easywork.equipment.domain.document.EquipmentDoc;
import com.project.easywork.plan.domain.MeasurementField;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record DraftSourceMaterial(
    String referenceNumber,
    LocalDate measureDate,
    MeasurementField measurementField,
    String measurementType,
    Stack stack,
    List<StackMeasurement> measurementItems,
    Team team,
    String vehicleNumber,
    String mentor,
    String mentee,
    EquipmentDoc particleSampler,
    EquipmentDoc gasSampler,
    EquipmentDoc pitotTube,
    EquipmentDoc nozzle
) {}