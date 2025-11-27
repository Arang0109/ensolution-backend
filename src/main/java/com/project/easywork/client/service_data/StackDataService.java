package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.Stack;

public interface StackDataService {
  Stack findStackById(Long stackId);

}
