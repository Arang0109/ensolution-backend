package com.project.easywork.client.domain.dto.stack;

import com.project.easywork.common.constant.Grade;
import com.project.easywork.common.constant.Orientation;
import com.project.easywork.common.constant.Shape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class StackUpdateRequestDto {
  private String name;
  private String semsNumber;
  private Grade grade;
  private Double height;
  private Double horizontalLength;
  private Double verticalLength;
  private Shape shape;
  private Orientation orientation;
  private String remark;
}
