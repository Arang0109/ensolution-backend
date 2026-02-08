package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.document.input.PreInfoDoc;
import com.project.easywork.measurement.dto.snapshot.agency.AgencySnapshot;
import com.project.easywork.measurement.dto.snapshot.stack_measurement.StackMeasurementSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = StackMeasurementDocMapper.class,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AgencyDocMapper {
  
  
  @Mapping(target = "teamId", source = "snapshot.team.teamId")
  @Mapping(target = "teamName", source = "snapshot.team.name")
  @Mapping(target = "measurementItems", source = "items")
  PreInfoDoc toDoc(
      AgencySnapshot snapshot,
      List<StackMeasurementSnapshot> items
  );
}
