package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.document.input.MeasurementItemDoc;
import com.project.easywork.measurement.dto.snapshot.stack_measurement.StackMeasurementSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MeasurementItemDocMapper {
  MeasurementItemDoc toDoc(StackMeasurementSnapshot snapshot);
  
  default String toName(MeasurementItemDoc doc) {
    return doc == null ? null : doc.getPollutantNameKr();
  }
  
  List<MeasurementItemDoc> toDocs(List<StackMeasurementSnapshot> snapshots);
}
