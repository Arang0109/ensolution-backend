package com.project.easywork.agency.domain.dto;

import com.project.easywork.user.domain.dto.UserD;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class TeamDetailD {
  private TeamD team;
  private List<UserD> users = new ArrayList<>();
}