package com.project.easywork.plan.domain;

public enum PlanStatus {
  MEASURING,
  ANALYZING,
  COMPLETED,
  CANCELED;
  
  public boolean canEdit() {
    return this == MEASURING || this == ANALYZING;
  }
}