package com.project.easywork.measurement.domain.dto.draft_source;

import com.project.easywork.measurement.domain.dto.draft_source.basic_info.BasicInfoSource;
import com.project.easywork.measurement.domain.dto.draft_source.client.ClientSource;
import com.project.easywork.measurement.domain.dto.draft_source.equipments.EquipmentSource;
import com.project.easywork.measurement.domain.dto.draft_source.items.MeasurementItemSource;
import com.project.easywork.measurement.domain.dto.draft_source.team.TeamSource;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "측정 계획 보고서 원본 데이터")
public record DraftSource(
    
    /* 측정계획 기초정보 스냅샷 */
    BasicInfoSource basicInfo,
    
    TeamSource team,
    
    /* 의뢰기관 스냅샷 */
    ClientSource client,
    
    /* 측정장비 스냅샷 */
    EquipmentSource equipment,
    
    /* 측정물질 스냅샷 */
    List<MeasurementItemSource> items
) {}