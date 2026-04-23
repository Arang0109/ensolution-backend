package com.project.easywork.measurement.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
  
  private final Calculator calculator = new Calculator();
  
  @Test
  @DisplayName("round method 가 정상작동하는가?")
  void round_test() {
    BigDecimal result = calculator.round(new BigDecimal("1.235"), 2);
    
    assertEquals(new BigDecimal("1.24"), result);
  }
  
  @Test
  @DisplayName("리스트가 null 이면 0을 반환한다.")
  void list_is_null_return_0() {
    BigDecimal result = calculator.averageTreatNullAsZero(null, 2);
    assertEquals(BigDecimal.ZERO, result);
  }
  
  @Test
  @DisplayName("리스트가 비어있으면 0을 반환한다.")
  void list_is_empty_return_0() {
    BigDecimal result = calculator.averageTreatNullAsZero(List.of(), 2);
    assertEquals(BigDecimal.ZERO, result);
  }
  
  @Test
  @DisplayName("null 은 0으로 처리한다.")
  void null_is_0() {
    List<BigDecimal> list = Arrays.asList(
        new BigDecimal("10"),
        null,
        new BigDecimal("20")
    );
    
    BigDecimal result = calculator.averageTreatNullAsZero(list, 2);
    
    assertEquals(new BigDecimal("10.00"), result);
  }
  
  @Test
  void 정상값_평균을_계산한다() {
    List<BigDecimal> list = List.of(
        new BigDecimal("10"),
        new BigDecimal("20")
    );
    
    BigDecimal result = calculator.averageTreatNullAsZero(list, 2);
    
    assertEquals(new BigDecimal("15.00"), result);
  }
  
  @Test
  void 반올림이_정상동작한다() {
    List<BigDecimal> list = List.of(
        new BigDecimal("10"),
        new BigDecimal("11")
    );
    
    BigDecimal result = calculator.averageTreatNullAsZero(list, 1);
    
    assertEquals(new BigDecimal("10.5"), result);
  }
}