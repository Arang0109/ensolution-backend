package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.plan.domain.dto.PlanTableViewD;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    uses = {
        MeasurementItemDocMapper.class
    }
)
public interface MeasurementDocMapper {
  
  @Mapping(target = "id", source = "planId")
  @Mapping(target = "companyName", source = "client.company.companyName")
  @Mapping(target = "workplaceName", source = "client.company.workplaceName")
  @Mapping(target = "stackName", source = "client.stack.name")
  @Mapping(target = "measurementItems", source = "measurementItems")
  PlanTableViewD toTable(MeasurementDoc doc);
  
  List<PlanTableViewD> toTableList(List<MeasurementDoc> docs);
}
