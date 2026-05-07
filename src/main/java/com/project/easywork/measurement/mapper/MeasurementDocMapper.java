package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.domain.document.MeasurementDoc;
import com.project.easywork.plan.domain.dto.PlanTableViewD;
import com.project.easywork.report.domain.client.PreDataD;
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
  @Mapping(target = "measurementField", source = "basicInfo.measurementField")
  @Mapping(target = "measureDate", source = "basicInfo.measureDate")
  @Mapping(target = "measurementType", source = "basicInfo.measurementType")
  @Mapping(target = "companyName", source = "client.company.companyName")
  @Mapping(target = "workplaceName", source = "client.company.workplaceName")
  @Mapping(target = "stackName", source = "client.stack.name")
  @Mapping(target = "teamName", source = "team.teamName")
  @Mapping(target = "measurementItems", source = "measurementItems")
  PlanTableViewD toTable(MeasurementDoc doc);
  
  List<PlanTableViewD> toTableList(List<MeasurementDoc> docs);
  
  @Mapping(target = "referenceNumber", source = "basicInfo.referenceNumber")
  @Mapping(target = "measureDate", source = "basicInfo.measureDate")
  @Mapping(target = "receivedDate", source = "basicInfo.receivedDate")
  @Mapping(target = "analysisDate", source = "basicInfo.analysisDate")
  @Mapping(target = "mentor", source = "team.mentor")
  @Mapping(target = "mentee", source = "team.mentee")
  @Mapping(target = "measureStartTime", source = "basicInfo.measureStartTime")
  @Mapping(target = "measureEndTime", source = "basicInfo.measureEndTime")
  PreDataD toPreDataDto(MeasurementDoc doc);
}
