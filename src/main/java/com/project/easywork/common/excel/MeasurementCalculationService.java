package com.project.easywork.common.excel;

import com.project.easywork.client.domain.dto.stack.StackDetailResponseDto;
import com.project.easywork.common.excel.dto.MeasurementData;
import com.project.easywork.client.service.IStackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasurementCalculationService {

  private final IStackService IStackService;
  
  private void measurementPointCalculate(Long stackId, MeasurementData data) {
    StackDetailResponseDto stack = IStackService.getStack(stackId);
    
    double horizontal = stack.getStack().getHorizontalLength(); // 가로 (2R)
    double vertical = stack.getStack().getVerticalLength();     // 세로 (높이 or 한 변)
    String shape = String.valueOf(stack.getStack().getShape());
    
    // 원형 굴뚝
    if ("CIRCULAR".equalsIgnoreCase(shape)) {
      double radius = horizontal / 2.0;
      
      // 반경 비율 설정
      double[] ratios;
      if (horizontal <= 1.0) {
        ratios = new double[]{0.707};
      } else if (horizontal <= 2.0) {
        ratios = new double[]{0.500, 0.866};
      } else if (horizontal <= 4.0) {
        ratios = new double[]{0.408, 0.707, 0.913};
      } else if (horizontal <= 4.5) {
        ratios = new double[]{0.354, 0.612, 0.791, 0.935};
      } else {
        ratios = new double[]{0.316, 0.548, 0.707, 0.837, 0.949};
      }
      
      // 실제 거리(m) 계산
      double[] distances = new double[ratios.length];
      for (int i = 0; i < ratios.length; i++) {
        distances[i] = radius - radius * ratios[i];
      }
      data.setDistances(distances);
      return;
    }
    
    // 사각형 굴뚝
    if ("RECTANGULAR".equalsIgnoreCase(shape)) {
      if (horizontal <= 0 || vertical <= 0) {
        throw new IllegalArgumentException("사각형 굴뚝의 가로, 세로 길이는 0보다 커야 합니다.");
      }
      
      // 측정점 계산
    }
    
    throw new IllegalArgumentException("지원하지 않는 형상 타입입니다: " + shape);
  }
  
}
