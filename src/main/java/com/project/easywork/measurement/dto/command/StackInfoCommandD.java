package com.project.easywork.measurement.dto.command;

import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "측정시설 정보")
public record StackInfoCommandD(
    @Schema(description = "굴뚝 높이(m)", example = "20.5")
    BigDecimal height,
    
    @Schema(description = "수평 길이(m)", example = "1.0")
    BigDecimal horizontalLength,
    
    @Schema(description = "수직 길이(m)", example = "1.0")
    BigDecimal verticalLength,
    
    @Schema(description = "굴뚝 형상 (사각/원형)", example = "CIRCULAR")
    Shape shape,
    
    @Schema(description = "굴뚝 타입 (수직/수평)", example = "VERTICAL")
    Orientation orientation,
    
    @Schema(description = "표준산소농도 (%)", example = "21.0")
    BigDecimal standardOxygen
) {}