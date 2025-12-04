package com.project.easywork.agency.domain.dto;

import com.project.easywork.user.domain.dto.UserResponseDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class TeamDetailResponseDto {
  private TeamResponseDto team;
  private List<UserResponseDto> users = new ArrayList<>();
  private List<VehicleResponseDto> vehicles = new ArrayList<>();
}