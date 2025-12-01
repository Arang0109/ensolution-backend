package com.project.easywork.client.domain.dto.prevention;

import com.project.easywork.client.domain.dto.facility.FacilityResponseDto;
import com.project.easywork.client.domain.dto.target.TargetResponseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class PreventionDetailResponseDto {
  private PreventionResponseDto prevention;
  private List<FacilityResponseDto> facilities = new ArrayList<>();
  private List<TargetResponseDto> targets = new ArrayList<>();
}
