package com.project.easywork.measurement.service.impl;

import com.project.easywork.measurement.dto.MeasurementStatus;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.dto.document.input.PreInfoDoc;
import com.project.easywork.measurement.dto.snapshot.MeasurementSnapshot;
import com.project.easywork.measurement.mapper.ClientDocMapper;
import com.project.easywork.measurement.mapper.StackMeasurementDocMapper;
import com.project.easywork.measurement.util.MeasurementPointCalculator;
import com.project.easywork.plan.domain.dto.PlanCreateD;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MeasurementDocumentFactory {
  
  private final MeasurementPointCalculator measurementPointCalculator;
  private final ClientDocMapper clientDocMapper;
  private final StackMeasurementDocMapper stackMeasurementDocMapper;
  
  public MeasurementDoc createDraft(Long planId, PlanCreateD plan, MeasurementSnapshot s) {
  
    return MeasurementDoc.builder()
        .planId(planId)
        .status(MeasurementStatus.DRAFT)
        .measurementPointCnt(measurementPointCalculator.calculate(
            s.client().stack().shape(),
            s.client().stack().horizontalLength(),
            s.client().stack().verticalLength()
        ))
        .preInfo(buildPreInfo(plan, s))
        .client(clientDocMapper.toDoc(s.client()))
        .build();
  }
  
  private PreInfoDoc buildPreInfo(PlanCreateD plan, MeasurementSnapshot s) {
    return PreInfoDoc.builder()
        .measureDate(plan.getMeasureDate())
        .measurementType(plan.getMeasurementType())
        .teamId(s.agency().team().teamId())
        .teamName(s.agency().team().name())
        .vehicleNumber(s.agency().vehicleNumber())
        .mentor(s.agency().mentor())
        .mentee(s.agency().mentee())
        .simplifiedMeasurement(true) // 추후 수정
        .measurementItems(stackMeasurementDocMapper.toDocs(s.stackMeasurements()))
        .build();
  }
  
  private String safe(String value) {
    return value == null ? "" : value;
  }
  
  private <T> List<T> safeList(List<T> list) {
    return list == null ? List.of() : list;
  }
}