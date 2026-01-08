package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.prevention.*;

import java.util.List;

public interface IPreventionService {
  PreventionDetailD registerPreventionBundle(PreventionBundleCreateD requestDto);
  PreventionDetailD getPrevention(Long preventionId);
  List<PreventionD> getPreventions();
  List<PreventionD> getPreventionsByStack(Long stackId);
  PreventionDetailD updatePrevention(Long preventionId, PreventionBundleUpdateD requestDto);
  void removePrevention(Long preventionId);
}
