package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.domain.document.items.MeasurementItemSnapshotDoc;
import com.project.easywork.measurement.domain.dto.draft_source.items.MeasurementItemSource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface MeasurementItemDocMapper {
  MeasurementItemSnapshotDoc toDoc(MeasurementItemSource snapshot);
  
  default String toName(MeasurementItemSnapshotDoc doc) {
    return doc == null ? null : doc.getPollutantNameKr();
  }
  
  List<MeasurementItemSnapshotDoc> toDocs(List<MeasurementItemSource> snapshots);
}
