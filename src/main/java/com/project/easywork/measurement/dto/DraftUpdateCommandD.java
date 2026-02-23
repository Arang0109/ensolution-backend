package com.project.easywork.measurement.dto;

import com.project.easywork.measurement.dto.document.input.ClientDoc;
import com.project.easywork.measurement.dto.document.input.PreInfoDoc;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "측정 데이터 임시저장 요청 DTO")
public record DraftUpdateCommandD(
    PreInfoDoc preInfo,
    ClientDoc client,
    List<Long> pollutantIdList,
    String particleSamplerId,
    String gasSamplerId,
    String pitotTubeId,
    String nozzleId
) {}