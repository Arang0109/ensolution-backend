package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.snapshot.MeasurementSnapshot;
import com.project.easywork.measurement.mapper.AgencyDocMapper;
import com.project.easywork.measurement.mapper.ClientDocMapper;
import com.project.easywork.measurement.mapper.EquipmentDocMapper;
import com.project.easywork.measurement.util.MeasurementPointCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeasurementDocumentFactory {
  
  private final MeasurementPointCalculator measurementPointCalculator;
  private final ClientDocMapper clientDocMapper;
  private final AgencyDocMapper agencyDocMapper;
  private final EquipmentDocMapper equipmentDocMapper;
  
  public MeasurementDoc createDraft(Long planId, MeasurementSnapshot s) {
  
    return MeasurementDoc.builder()
        .planId(planId)
        .status(MeasurementStatus.DRAFT)
        .measurementPointCnt(measurementPointCalculator.calculate(
            s.client().stack().shape(),
            s.client().stack().horizontalLength(),
            s.client().stack().verticalLength()
        ))
        .preInfo(agencyDocMapper.toDoc(s.agency(), s.stackMeasurements()))
        .client(clientDocMapper.toDoc(s.client()))
        .equipment(equipmentDocMapper.toDoc(s.measurementEquipment()))
        .build();
  }
  
  private String safe(String value) {
    return value == null ? "" : value;
  }
  
  private <T> List<T> safeList(List<T> list) {
    return list == null ? List.of() : list;
  }
}