package com.project.easywork.measurement.dto;

import com.project.easywork.measurement.dto.command.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "측정 임시저장(초안) 업데이트 요청 DTO")
public record MeasurementDraftUpdateCommandDto(
    
    @Schema(description = "기초 정보")
    PreInfoCommandDto preInfo,
    
    @Schema(description = "의뢰기관 정보")
    ClientCommandDto client,
    
    @Schema(description = "기상 정보")
    WeatherCommandDto weather,
    
    @Schema(description = "수분 정보")
    MoistureCommandDto moisture,
    
    @Schema(description = "배출가스 정보")
    ExhaustGasCommandDto exhaustGas

) {}