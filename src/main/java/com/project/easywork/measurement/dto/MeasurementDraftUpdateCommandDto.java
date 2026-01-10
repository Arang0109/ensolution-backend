package com.project.easywork.measurement.dto;

import com.project.easywork.measurement.dto.command.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "측정 임시저장(초안) 업데이트 요청 DTO")
public record MeasurementDraftUpdateCommandDto(
    
    @Schema(description = "사전 정보")
    ClientCommandDto preInfo,
    
    @Schema(description = "기상 정보")
    WeatherCommandDto weather,
    
    @Schema(description = "수분 정보")
    MoistureCommandDto moisture,
    
    @Schema(description = "배출가스 정보")
    ExhaustGasCommandDto exhaustGas

) {}