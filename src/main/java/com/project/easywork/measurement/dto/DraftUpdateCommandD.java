package com.project.easywork.measurement.dto;

import com.project.easywork.measurement.dto.command.*;
import com.project.easywork.measurement.mapper.ClientMapper;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "측정 임시저장(초안) 업데이트 요청 DTO")
public record DraftUpdateCommandD(
    @Schema(description = "의뢰기관 정보")
    ClientCommandD client,
    
    @Schema(description = "기상 정보")
    WeatherCommandD weather,
    
    @Schema(description = "수분 정보")
    MoistureCommandD moisture,
    
    @Schema(description = "배출가스 정보")
    ExhaustGasCommandD exhaustGas
) {}