package com.project.easywork.measurement.dto.snapshot;

import com.project.easywork.measurement.dto.snapshot.agency.AgencySnapshot;
import com.project.easywork.measurement.dto.snapshot.client.ClientSnapshot;
import com.project.easywork.measurement.dto.snapshot.equipment.MeasurementEquipmentSnapshot;
import com.project.easywork.measurement.dto.snapshot.stack_measurement.StackMeasurementSnapshot;

import java.util.List;

public record MeasurementSnapshot(
    
    /* 자가측정 대행업체 스냅샷 */
    AgencySnapshot agency,
    
    /* 의뢰기관 스냅샷 */
    ClientSnapshot client,
    
    /* 측정물질 스냅샷 */
    List<StackMeasurementSnapshot> stackMeasurements,
    
    /* 측정장비 스냅샷 */
    MeasurementEquipmentSnapshot measurementEquipment
) {}