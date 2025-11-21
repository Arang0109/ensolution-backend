package com.project.easywork.client.service;

import com.project.easywork.client.dto.view.StackDetailDto;

public interface StackService {
  StackDetailDto getStack(Long stackId);
}