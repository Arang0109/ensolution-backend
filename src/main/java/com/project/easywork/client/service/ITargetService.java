package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.target.TargetCreateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetResponseDto;
import com.project.easywork.client.domain.dto.target.TargetUpdateRequestDto;

import java.util.List;

public interface ITargetService {
  void registerTarget(TargetCreateRequestDto requestDto);
  
  List<TargetResponseDto> getTargets();
  
  TargetResponseDto updateTarget(Long targetId, TargetUpdateRequestDto requestDto);
  
  void removeTarget(Long targetId);
}
