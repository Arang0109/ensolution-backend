package com.project.easywork.report.domain.client;

import com.project.easywork.client.domain.Grade;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDataD {
  private String companyName;
  private String workplaceName;
  private String ceoName;
  private String address;
  private String bizNumber;
  private String manager;
  private String businessCategory;
  private Grade grade;
}