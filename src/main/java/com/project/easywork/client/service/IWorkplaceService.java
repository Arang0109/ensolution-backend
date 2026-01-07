package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.workplace.*;

import java.util.List;

public interface IWorkplaceService {
  WorkplaceD registerWorkplace(WorkplaceCreateD requestDto);
  WorkplaceDetailD getWorkplace(Long workplaceId);
  List<WorkplaceD> getWorkplaces();
  WorkplaceD updateWorkplace(Long workplaceId, WorkplaceUpdateD requestDto);
  void removeWorkplace(Long workplaceId);
}