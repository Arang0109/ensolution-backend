package com.project.easywork.measurement.service.impl.draft;

import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.MeasurementSheetDoc;
import com.project.easywork.measurement.dto.snapshot.DraftSnapshot;
import com.project.easywork.measurement.dto.snapshot.plan_info.PlanInfoSnapshot;
import com.project.easywork.measurement.mapper.ClientDocMapper;
import com.project.easywork.measurement.mapper.EquipmentDocMapper;
import com.project.easywork.measurement.mapper.MeasurementItemDocMapper;
import com.project.easywork.measurement.util.MeasurementPointCalculator;
import com.project.easywork.plan.domain.MeasurementCategory;
import com.project.easywork.plan.domain.PlanStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DraftCreateFactory {
  
  private final MeasurementPointCalculator measurementPointCalculator;
  private final ClientDocMapper clientDocMapper;
  private final EquipmentDocMapper equipmentDocMapper;
  private final MeasurementItemDocMapper measurementItemDocMapper;
  
  public MeasurementDoc createDraft(Long planId, DraftSnapshot s) {
    
    PlanInfoSnapshot planInfo = s.planInfo();
  
    return MeasurementDoc.builder()
        .planId(planId)
        .teamId(planInfo.team().teamId())
        .status(PlanStatus.MEASURING)
        .referenceNumber(planInfo.referenceNumber())
        .measureDate(planInfo.measureDate())
        .measurementField(planInfo.measurementField())
        .measurementType(planInfo.measurementType())
        .teamName(planInfo.team().name())
        .vehicleNumber(planInfo.vehicleNumber())
        .mentor(planInfo.mentor())
        .mentee(planInfo.mentee())
        
        .client(clientDocMapper.toDoc(s.client()))
        .equipment(equipmentDocMapper.toDoc(s.measurementEquipment()))
        .measurementItems(measurementItemDocMapper.toDocs(s.stackMeasurements()))
        
        .sheets(List.of(createDefaultSheet()))
        
        .measurementPointCnt(measurementPointCalculator.calculate(
            s.client().stack().shape(),
            s.client().stack().horizontalLength(),
            s.client().stack().verticalLength()
        ))
        .build();
  }
  
  private MeasurementSheetDoc createDefaultSheet() {
    return MeasurementSheetDoc.builder()
        .category(MeasurementCategory.OTHER)
        .build();
  }
}