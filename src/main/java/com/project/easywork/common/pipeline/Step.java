package com.project.easywork.common.pipeline;

public interface Step<T extends Context> {
  void execute(T context);
}