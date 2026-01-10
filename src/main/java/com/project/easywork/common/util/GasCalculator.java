package com.project.easywork.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class GasCalculator {
  
  // 표준 몰부피 (L/mol)
  private static final BigDecimal STD_AIR_VOLUME = new BigDecimal("22.4");
  
  private static final BigDecimal BD_273 = new BigDecimal("273");
  private static final BigDecimal BD_760 = new BigDecimal("760");
  private static final BigDecimal BD_100 = new BigDecimal("100");
  
  // 분자량
  private static final BigDecimal MOL_O2  = new BigDecimal("32.0");
  private static final BigDecimal MOL_CO2 = new BigDecimal("44.0");
  private static final BigDecimal MOL_N2  = new BigDecimal("28.0");
  private static final BigDecimal MOL_H2O = new BigDecimal("18.0");
  
  /**
   * 실제 밀도 보정
   * V_actual = V_std × (273 / (273 + T)) × (P / 760)
   */
  public static BigDecimal toActualDensity(BigDecimal gasVolume,
                                           BigDecimal temp,
                                           BigDecimal pressure) {
    
    BigDecimal term1 = BD_273.divide(BD_273.add(temp), 10, RoundingMode.HALF_UP);
    BigDecimal term2 = pressure.divide(BD_760, 10, RoundingMode.HALF_UP);
    
    return gasVolume
        .multiply(term1)
        .multiply(term2)
        .setScale(10, RoundingMode.HALF_UP);
  }
  
  /**
   * 평균값 계산 (BigDecimal 리스트 기반)
   */
  public static BigDecimal avg(List<BigDecimal> values) {
    if (values == null || values.isEmpty()) {
      return BigDecimal.ZERO;
    }
    
    BigDecimal sum = BigDecimal.ZERO;
    int count = 0;
    
    for (BigDecimal v : values) {
      if (v != null) {
        sum = sum.add(v);
        count++;
      }
    }
    
    if (count == 0) {
      return BigDecimal.ZERO;
    }
    
    return sum.divide(BigDecimal.valueOf(count), 10, RoundingMode.HALF_UP);
  }
  
  /**
   * 혼합가스 밀도 계산
   *
   * ρ = Σ (분자량 / 22.4) × (성분비율)
   * 성분비율은 % 단위로 들어온다고 가정
   */
  public static BigDecimal calGasDensity(BigDecimal o2,
                                         BigDecimal co2,
                                         BigDecimal co,
                                         BigDecimal moisture) {
    
    // N2 = 100 - (O2 + CO2 + CO + H2O)
    BigDecimal n2 = BD_100.subtract(
        o2.add(co2).add(co).add(moisture)
    );
    
    BigDecimal result = BigDecimal.ZERO;
    
    result = result.add(
        MOL_O2.divide(STD_AIR_VOLUME, 10, RoundingMode.HALF_UP)
            .multiply(o2.divide(BD_100, 10, RoundingMode.HALF_UP))
    );
    
    result = result.add(
        MOL_CO2.divide(STD_AIR_VOLUME, 10, RoundingMode.HALF_UP)
            .multiply(co2.divide(BD_100, 10, RoundingMode.HALF_UP))
    );
    
    result = result.add(
        MOL_N2.divide(STD_AIR_VOLUME, 10, RoundingMode.HALF_UP)
            .multiply(n2.divide(BD_100, 10, RoundingMode.HALF_UP))
    );
    
    result = result.add(
        MOL_H2O.divide(STD_AIR_VOLUME, 10, RoundingMode.HALF_UP)
            .multiply(moisture.divide(BD_100, 10, RoundingMode.HALF_UP))
    );
    
    return result.setScale(10, RoundingMode.HALF_UP);
  }
}