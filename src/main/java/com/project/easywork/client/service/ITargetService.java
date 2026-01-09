package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.target.TargetCreateD;
import com.project.easywork.client.domain.dto.target.TargetD;
import com.project.easywork.client.domain.persistance.Prevention;

import java.util.List;

public interface ITargetService {
  void registerTargets(List<TargetCreateD> requestDtos, Prevention prevention);
  
  List<TargetD> getTargets();
  
  void removeTarget(Long preventionId, Long targetId);
}
