package com.project.easywork.measurement.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

public record ExhaustGasCommandD(
    
    @Schema(description = "산소농도(%)", example = "[20.9, 20.8, 20.9]")
    List<BigDecimal> o2Concentration,
    
    @Schema(description = "이산화탄소농도(%)", example = "[0.1, 0.1, 0.2]")
    List<BigDecimal> co2Concentration,
    
    @Schema(description = "일산화탄소농도(ppm)", example = "[10, 12, 11]")
    List<BigDecimal> coConcentration,
    
    @Schema(description = "질소산화물(ppm)", example = "[3.2, 3.4, 3.3]")
    List<BigDecimal> noxConcentration,
    
    @Schema(description = "황산화물(ppm)", example = "[0, 0, 0]")
    List<BigDecimal> soxConcentration
    
) {}
