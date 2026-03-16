package com.project.easywork.measurement.dto.snapshot;

import com.project.easywork.measurement.dto.snapshot.plan_info.PlanInfoSnapshot;
import com.project.easywork.measurement.dto.snapshot.client.ClientSnapshot;
import com.project.easywork.measurement.dto.snapshot.equipment.MeasurementEquipmentSnapshot;
import com.project.easywork.measurement.dto.snapshot.stack_measurement.StackMeasurementSnapshot;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "측정 계획 보고서 초안 snapshot")
public record DraftSnapshot(
    
    /* 측정계획 기초정보 스냅샷 */
    PlanInfoSnapshot planInfo,
    
    /* 의뢰기관 스냅샷 */
    ClientSnapshot client,
    
    /* 측정물질 스냅샷 */
    List<StackMeasurementSnapshot> stackMeasurements,
    
    /* 측정장비 스냅샷 */
    MeasurementEquipmentSnapshot measurementEquipment
) {}