package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.pipeline.SheetContext;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(999)
@RequiredArgsConstructor
public class ApplyResultStep implements SheetStep {
  
  @Override
  public void execute(SheetContext context) {
//    context.getSheet().updateFromContext(context);
  }
}
