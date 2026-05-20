package com.project.easywork.measurement.pipeline.step;

import com.project.easywork.measurement.domain.document.client.ClientSnapshotDoc;
import com.project.easywork.measurement.domain.document.sheets.MeasurementPointDoc;
import com.project.easywork.measurement.pipeline.SheetContext;
import com.project.easywork.measurement.pipeline.domain.Sheet;
import com.project.easywork.measurement.util.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class InitStep implements SheetStep {
  
  private final Calculator calculator;
  
  @Override
  public void execute(SheetContext context) {
    Sheet sheet = context.getSheet();
    ClientSnapshotDoc client = context.getClient();
    List<MeasurementPointDoc> measurementPoints = sheet.getSheet().getMeasurementPoints();
    
    BigDecimal standardOxygen = client.getStack().getStandardOxygen();
    if (standardOxygen != null) {
      context.setStandardOxygen(standardOxygen);
    }
    
    BigDecimal avgTg = calculator.averageTreatNullAsZero(
        measurementPoints.stream()
            .map(mp -> {
              BigDecimal ts = mp.getTs();
              return ts == null ? null : ts.add(BigDecimal.valueOf(273));
            })
            .toList(), 1
    );
    
    BigDecimal avgPv = calculator.averageTreatNullAsZero(
        measurementPoints.stream()
            .map(MeasurementPointDoc::getPv)
            .toList(), 1
    );
    
    BigDecimal avgPs = calculator.averageTreatNullAsZero(
        measurementPoints.stream().map(MeasurementPointDoc::getPs).toList(), 1
    );
    
    context.setAvgTg(avgTg);
    context.setAvgPv(avgPv);
    context.setAvgPs(avgPs);
  }
}
