package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.target.TargetCreateD;
import com.project.easywork.client.domain.dto.target.TargetD;
import com.project.easywork.client.domain.dto.target.TargetUpdateD;
import com.project.easywork.client.domain.persistance.Prevention;

import java.util.List;

public interface ITargetService {
  TargetD registerTarget(TargetCreateD requestDto);
  
  List<TargetD> registerTargets(List<TargetCreateD> requestDtos, Prevention prevention);
  
  List<TargetD> getTargets();
  
  TargetD updateTarget(Long targetId, TargetUpdateD requestDto);
  
  void removeTarget(Long targetId);
}
