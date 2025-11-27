package com.project.easywork.client.domain.dto.company;

import com.project.easywork.client.domain.dto.workplace.WorkplaceResponseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class CompanyDetailResponseDto {
  private CompanyResponseDto company;
  private List<WorkplaceResponseDto> workplaces = new ArrayList<>();
}