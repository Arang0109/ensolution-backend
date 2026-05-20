package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.prevention.PreventionBundleCreateD;
import com.project.easywork.client.domain.dto.prevention.PreventionBundleUpdateD;
import com.project.easywork.client.domain.dto.prevention.PreventionD;
import com.project.easywork.client.domain.dto.prevention.PreventionDetailD;

import java.util.List;

public interface IPreventionService {
  PreventionDetailD registerPreventionBundle(PreventionBundleCreateD requestDto);
  PreventionDetailD getPrevention(Long preventionId);
  List<PreventionD> getPreventions();
  List<PreventionD> getPreventionsByStack(Long stackId);
  PreventionDetailD updatePrevention(Long preventionId, PreventionBundleUpdateD requestDto);
  void removePrevention(Long preventionId);
}
