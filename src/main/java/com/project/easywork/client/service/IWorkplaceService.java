package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.workplace.WorkplaceCreateD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceDetailD;
import com.project.easywork.client.domain.dto.workplace.WorkplaceUpdateD;

import java.util.List;

public interface IWorkplaceService {
  WorkplaceD registerWorkplace(WorkplaceCreateD requestDto);
  WorkplaceDetailD getWorkplace(Long workplaceId);
  List<WorkplaceD> getWorkplaces();
  WorkplaceD updateWorkplace(Long workplaceId, WorkplaceUpdateD requestDto);
  void removeWorkplace(Long workplaceId);
}