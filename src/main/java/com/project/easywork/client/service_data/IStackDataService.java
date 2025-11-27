package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.Stack;

import java.util.List;

public interface IStackDataService {
  Stack findById(Long stackId);
  void saveStack(Stack stack);
  void deleteStack(Long stackId);
  List<Stack> findAllStacks();
  List<Stack> findStacksByWorkplaceId(Long workplaceId);
}
