package com.project.easywork.measurement.dto.command;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "수분 정보 입력 DTO")
public record MoistureCommandD(
    
    @Schema(description = "흡습병 무게(g)")
    Weight weight,
  
    @Schema(description = "가스미터 온도[가스상 시료채취장비]")
    GasMeterTemperature gasMeterTemperature,
    
    @Schema(description = "흡입 건조가스 부피(L)")
    DryGasVolume dryGasVolume,
    
    @Schema(description = "흡인 유속 (L/min)", example = "1.0")
    BigDecimal suctionVelocity,
    
    @Schema(description = "가스미터 게이지압[가스상 시료채취장비](L/min)", example = "1.0")
    BigDecimal gasMeterGaugePressure
) {
  
  @Schema(description = "흡습병 무게(g)")
  public record Weight(
      
      @Schema(description = "채취 전 흡습병 무게(g)", example = "0.00")
      BigDecimal before,
      
      @Schema(description = "채취 후 흡습병 무게(g)", example = "1.21")
      BigDecimal after
  ) {}
  
  @Schema(description = "가스미터 온도[가스상 시료채취장비]")
  public record GasMeterTemperature(
      
      @Schema(description = "가스미터 입구 온도", example = "21.1")
      BigDecimal in,
      
      @Schema(description = "가스미터 출구 온도", example = "21.1")
      BigDecimal out
  ) {}
  
  @Schema(description = "흡입 건조가스 부피(L)")
  public record DryGasVolume(
      
      @Schema(description = "채취 전 건조가스 부피", example = "0.000")
      BigDecimal before,
      
      @Schema(description = "채취 후 건조가스 부피", example = "8.000")
      BigDecimal after
  ) {}
}
