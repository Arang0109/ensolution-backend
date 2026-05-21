package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.domain.document.team.TeamSnapshotDoc;
import com.project.easywork.measurement.domain.dto.command.SaveDraftCommandD;
import com.project.easywork.measurement.domain.dto.draft_source.team.TeamSource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TeamDocMapper {
  
  @Mapping(target = "teamName", source="name")
  TeamSnapshotDoc toDoc(TeamSource source);
  
  TeamSnapshotDoc toPatch(SaveDraftCommandD command);
}