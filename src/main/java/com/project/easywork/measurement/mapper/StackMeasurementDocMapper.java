package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.document.input.PreInfoDoc;
import com.project.easywork.measurement.dto.snapshot.stack_measurement.StackMeasurementSnapshot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StackMeasurementDocMapper {
  
  public PreInfoDoc.StackMeasurementDoc toDoc(
      StackMeasurementSnapshot snapshot
  ) {
    
    if (snapshot == null) return null;
    
    return PreInfoDoc.StackMeasurementDoc.builder()
        .stackMeasurementId(snapshot.stackMeasurementId())
        .pollutantId(snapshot.pollutantId())
        .pollutantNameKr(snapshot.pollutantNameKr())
        .pollutantNameEn(snapshot.pollutantNameEn())
        .method(snapshot.method())
        .equipmentName(snapshot.equipmentName())
        .testMethodName(snapshot.testMethodName())
        .samplingTime(snapshot.samplingTime())
        .samplingVolume(snapshot.samplingVolume())
        .cycle(snapshot.cycle())
        .allowance(snapshot.allowance())
        .build();
  }
  
  public List<PreInfoDoc.StackMeasurementDoc> toDocs(
      List<StackMeasurementSnapshot> snapshots
  ) {
    
    if (snapshots == null) return List.of();
    
    return snapshots.stream()
        .map(this::toDoc)
        .toList();
  }
}
