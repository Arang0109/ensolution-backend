package com.project.easywork.measurement.service.impl.processor;

import com.project.easywork.measurement.dto.document.input.ClientDoc;
import com.project.easywork.measurement.dto.document.input.MeasurementEquipmentDoc;
import com.project.easywork.measurement.dto.document.input.MeasurementSheetDoc;
import com.project.easywork.common.pipeline.Pipeline;
import com.project.easywork.measurement.pipeline.SheetContext;
import com.project.easywork.measurement.pipeline.domain.Sheet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SheetResultProcessor {
  
  private final Pipeline<SheetContext> pipeline;
  
  public MeasurementSheetDoc process(
      MeasurementSheetDoc measurementSheetDoc, ClientDoc client, MeasurementEquipmentDoc equipment
  ) {
    Sheet sheet = Sheet.builder()
        .sheet(measurementSheetDoc)
        .build();

    SheetContext sheetContext = new SheetContext(client, equipment, sheet);
    
    if (client != null &&
        client.getStack() != null &&
        client.getStack().getStandardOxygen() != null) {
      sheetContext.setStandardOxygen(client.getStack().getStandardOxygen());
    }
    
    pipeline.execute(sheetContext);
    
    return sheet.getSheet();
  }
}
