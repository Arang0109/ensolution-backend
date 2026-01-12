package com.project.easywork.measurement.dto;

import com.project.easywork.measurement.dto.command.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "측정 데이터 임시저장 요청 DTO")
public record DraftUpdateCommandD(
    @Schema(description = "측정시설 재원 정보")
    StackInfoCommandD stackInfo,
    
    @Schema(description = "기상 정보")
    WeatherCommandD weather,
    
    @Schema(description = "수분 정보")
    MoistureCommandD moisture,
    
    @Schema(description = "배출가스 정보")
    ExhaustGasCommandD exhaustGas,
    
    @Schema(description = "각 측정점 측정 데이터 정보")
    List<MeasurementPointCommandD> measurementPointInfo
) {}