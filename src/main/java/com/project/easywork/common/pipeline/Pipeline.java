package com.project.easywork.common.pipeline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class Pipeline<T extends Context> {
  private final List<Step<T>> steps;
  
  public void execute(T context) {
    for (Step<T> step : steps) {
      step.execute(context);
    }
  }
}