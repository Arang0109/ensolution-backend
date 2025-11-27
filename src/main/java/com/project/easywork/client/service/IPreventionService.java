package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.prevention.PreventionCreaterequestDto;
import com.project.easywork.client.domain.dto.prevention.PreventionDetailResponseDto;
import com.project.easywork.client.domain.dto.prevention.PreventionResponseDto;
import com.project.easywork.client.domain.dto.prevention.PreventionUpdateRequestDto;

import java.util.List;

public interface IPreventionService {
  void registerPrevention(PreventionCreaterequestDto requestDto);
  
  PreventionDetailResponseDto getPrevention(Long preventionId);
  
  List<PreventionResponseDto> getPreventions();
  
  PreventionResponseDto updatePrevention(Long preventionId, PreventionUpdateRequestDto requestDto);
  
  void removePrevention(Long preventionId);
}
