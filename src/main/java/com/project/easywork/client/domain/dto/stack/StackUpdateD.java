package com.project.easywork.client.domain.dto.stack;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StackUpdateD {
  private String name;
  private String semsNumber;
  private Grade grade;
  private BigDecimal height;
  private BigDecimal horizontalLength;
  private BigDecimal verticalLength;
  private Shape shape;
  private Orientation orientation;
  private BigDecimal standardOxygen;
  private String remark;
}
