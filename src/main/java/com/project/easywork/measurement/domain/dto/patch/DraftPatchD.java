package com.project.easywork.measurement.domain.dto.patch;

import com.project.easywork.measurement.domain.document.basic_info.BasicInfoDoc;
import com.project.easywork.measurement.domain.document.client.ClientSnapshotDoc;
import com.project.easywork.measurement.domain.document.equipments.EquipmentSnapshotDoc;
import com.project.easywork.measurement.domain.document.items.MeasurementItemSnapshotDoc;
import com.project.easywork.measurement.domain.document.sheets.MeasurementSheetDoc;
import com.project.easywork.measurement.domain.document.team.TeamSnapshotDoc;
import lombok.Builder;

import java.util.List;

@Builder
public record DraftPatchD (
    
    BasicInfoDoc basicInfo,
    TeamSnapshotDoc team,
    ClientSnapshotDoc client,
    EquipmentSnapshotDoc equipment,
    List<MeasurementItemSnapshotDoc> measurementItems,
    
    List<MeasurementSheetDoc> sheets
) {
}
