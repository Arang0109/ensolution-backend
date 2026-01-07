package com.project.easywork.client.domain.dto.company;

import com.project.easywork.client.domain.dto.workplace.WorkplaceD;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailD {
  private CompanyD company;
  private List<WorkplaceD> workplaces = new ArrayList<>();
}