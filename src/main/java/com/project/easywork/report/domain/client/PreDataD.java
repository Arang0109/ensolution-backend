package com.project.easywork.report.domain.client;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PreDataD {
  private String referenceNumber;
  private LocalDate measureDate; // 측정날짜
  private LocalDate receivedDate; // 접수날짜
  private LocalDate analysisDate; // 분석날짜
  private String mentor; // 사수
  private String mentee; // 부사수
}