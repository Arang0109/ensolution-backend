package com.project.easywork.client.service_data;

import com.project.easywork.client.domain.persistance.Stack;

import java.util.List;

public interface IStackDataService {
  Stack findById(Long stackId);
  Stack save(Stack stack);
  void deleteById(Long stackId);
  List<Stack> findAll();
  List<Stack> findStacksByWorkplaceId(Long workplaceId);
}
