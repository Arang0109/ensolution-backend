package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.target.TargetCreateRequestDto;
import com.project.easywork.client.domain.dto.target.TargetResponseDto;
import com.project.easywork.client.domain.dto.target.TargetUpdateRequestDto;
import com.project.easywork.client.domain.persistance.Prevention;

import java.util.List;

public interface ITargetService {
  TargetResponseDto registerTarget(TargetCreateRequestDto requestDto);
  
  List<TargetResponseDto> registerTargets(List<TargetCreateRequestDto> requestDtos, Prevention prevention);
  
  List<TargetResponseDto> getTargets();
  
  TargetResponseDto updateTarget(Long targetId, TargetUpdateRequestDto requestDto);
  
  void removeTarget(Long targetId);
}
