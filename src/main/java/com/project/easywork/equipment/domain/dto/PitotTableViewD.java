package com.project.easywork.equipment.domain.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class PitotTableViewD {
  List<PitotDetailD> pitotTubeList;
}
