package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.stack.StackDetailDto;

public interface StackService {
  StackDetailDto getStack(Long stackId);
}