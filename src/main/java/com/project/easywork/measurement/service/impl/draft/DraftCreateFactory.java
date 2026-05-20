package com.project.easywork.measurement.service.impl.draft;

import com.project.easywork.measurement.domain.document.MeasurementDoc;
import com.project.easywork.measurement.domain.document.sheets.MeasurementSheetDoc;
import com.project.easywork.measurement.domain.dto.draft_source.DraftSource;
import com.project.easywork.measurement.mapper.*;
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
  
  private final BasicInfoDocMapper basicInfoDocMapper;
  private final TeamDocMapper teamDocMapper;
  private final ClientDocMapper clientDocMapper;
  private final EquipmentDocMapper equipmentDocMapper;
  private final MeasurementItemDocMapper measurementItemDocMapper;
  
  public MeasurementDoc createDraft(Long planId, DraftSource s) {
    Integer measurementPointCnt = measurementPointCalculator.calculate(
        s.client().stack().shape(),
        s.client().stack().horizontalLength(),
        s.client().stack().verticalLength()
    );
    
    return MeasurementDoc.builder()
      .planId(planId)
      .status(PlanStatus.MEASURING)
      
      .basicInfo(basicInfoDocMapper.toDoc(s.basicInfo(), measurementPointCnt))
      .team(teamDocMapper.toDoc(s.team()))
      .client(clientDocMapper.toDoc(s.client()))
      .equipment(equipmentDocMapper.toDoc(s.equipment()))
      .measurementItems(measurementItemDocMapper.toDocs(s.items()))
      
      .sheets(List.of(createDefaultSheet()))
      
      .build();
  }
  
  private MeasurementSheetDoc createDefaultSheet() {
    return MeasurementSheetDoc.builder()
      .category(MeasurementCategory.GAS)
      .build();
  }
}