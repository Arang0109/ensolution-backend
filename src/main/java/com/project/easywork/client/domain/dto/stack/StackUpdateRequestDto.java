package com.project.easywork.client.domain.dto.stack;

import com.project.easywork.client.domain.Grade;
import com.project.easywork.client.domain.Orientation;
import com.project.easywork.client.domain.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StackUpdateRequestDto {
  private String name;
  private String semsNumber;
  private Grade grade;
  private Double height;
  private Double horizontalLength;
  private Double verticalLength;
  private Shape shape;
  private Orientation orientation;
  private Double standardOxygen;
  private String remark;
}
