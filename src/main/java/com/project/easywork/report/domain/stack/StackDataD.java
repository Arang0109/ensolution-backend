package com.project.easywork.report.domain.stack;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StackDataD {
  private String name;
  private String semsNumber;
  private Grade grade;
  private BigDecimal height;
  private BigDecimal horizontalLength;
  private BigDecimal verticalLength;
  private Shape shape;
  private Orientation orientation;
  private BigDecimal standardOxygen;
  private List<PreventionDataD> preventions;
}