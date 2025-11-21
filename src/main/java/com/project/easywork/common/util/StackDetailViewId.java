package com.project.easywork.common.util;

import java.io.Serializable;
import java.util.Objects;

public class StackDetailViewId implements Serializable {
  private Long stackId;
  private Long preventionId;
  private Long facilityId;
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof StackDetailViewId)) return false;
    StackDetailViewId that = (StackDetailViewId) o;
    return Objects.equals(stackId, that.stackId)
        && Objects.equals(preventionId, that.preventionId)
        && Objects.equals(facilityId, that.facilityId);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(stackId, preventionId, facilityId);
  }
}
