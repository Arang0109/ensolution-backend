package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.prevention.*;

import java.util.List;

public interface IPreventionService {
  PreventionDetailResponseDto registerPreventionBundle(PreventionBundleCreateRequestDto requestDto);
  PreventionDetailResponseDto getPrevention(Long preventionId);
  List<PreventionResponseDto> getPreventions();
  PreventionResponseDto updatePrevention(Long preventionId, PreventionUpdateRequestDto requestDto);
  void removePrevention(Long preventionId);
}
