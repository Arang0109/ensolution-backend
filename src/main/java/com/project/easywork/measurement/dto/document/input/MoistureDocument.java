package com.project.easywork.measurement.dto.document.input;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MoistureDocument {
  
  private WeightDocument weight;
  private GasMeterTemperatureDocument gasMeterTemperature;
  private DryGasVolumeDocument dryGasVolume;
  
  private Double gasMeterGaugePressure;
  
  // ------------------------
  // 흡습병 무게
  // ------------------------
  @Getter
  @Builder
  public static class WeightDocument {
    private Double before;
    private Double after;
  }
  
  // ------------------------
  // 가스미터 온도
  // ------------------------
  @Getter
  @Builder
  public static class GasMeterTemperatureDocument {
    private Double in;
    private Double out;
  }
  
  // ------------------------
  // 건조가스 부피
  // ------------------------
  @Getter
  @Builder
  public static class DryGasVolumeDocument {
    private Double before;
    private Double after;
  }
}
