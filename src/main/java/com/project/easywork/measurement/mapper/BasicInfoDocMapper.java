package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.domain.document.basic_info.BasicInfoDoc;
import com.project.easywork.measurement.domain.dto.command.SaveDraftCommandD;
import com.project.easywork.measurement.domain.dto.draft_source.basic_info.BasicInfoSource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BasicInfoDocMapper {
  @Mapping(target = "measurementPointCnt", source = "pointCnt")
  BasicInfoDoc toDoc(BasicInfoSource source, Integer pointCnt);
  
  @Mapping(target = "measurementPointCnt", source = "pointCnt")
  BasicInfoDoc toPatch(SaveDraftCommandD commandD, Integer pointCnt);
}
