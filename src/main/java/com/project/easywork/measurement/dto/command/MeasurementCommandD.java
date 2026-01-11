package com.project.easywork.measurement.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "전체 측정 입력 DTO (사전정보 + 기상 정보 등)")
public record MeasurementCommandD(
    
    @Schema(description = "측정계획 정보")
    PreInfoCommandD preInfo,
    
    @Schema(description = "측정장비 정보")
    EquipmentCommandD equipment,
    
    @Schema(description = "사전 정보 (회사, 사업장, 굴뚝, 방지시설 등)")
    ClientCommandD client,
    
    @Schema(description = "기상 정보")
    WeatherCommandD weather,
    
    @Schema(description = "수분 정보")
    MoistureCommandD moisture,
    
    @Schema(description = "배출가스 정보")
    ExhaustGasCommandD exhaustGas
    
    /* pollutants, schedule 등 추가 예정 */

) {}
