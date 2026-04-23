package com.project.easywork.report.domain.sheet;

import lombok.*;

import java.time.LocalTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParticleSampleD {
  private LocalTime samplingStartTime;
  private LocalTime samplingEndTime;
  
  private String thimbleFilter;
  private String bgThimbleFilter;
}
