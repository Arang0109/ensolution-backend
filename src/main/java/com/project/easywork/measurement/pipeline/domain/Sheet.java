package com.project.easywork.measurement.pipeline.domain;

import com.project.easywork.measurement.dto.document.input.ClientDoc;
import com.project.easywork.measurement.dto.document.input.MeasurementSheetDoc;
import com.project.easywork.measurement.pipeline.SheetContext;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Sheet {
  private MeasurementSheetDoc sheet;
  
  public void updateFromContext(SheetContext context) {
    this.sheet = this.sheet.toBuilder()
        .weather(
            this.sheet.getWeather().toBuilder()
                .Pa(context.getPa())
                .build()
        )
        .moisture(
            this.sheet.getMoisture().toBuilder()
                .Xw(context.getXw())
                .build()
        )
        .exhaustGas(
            this.sheet.getExhaustGas().toBuilder()
                .gasDensity(context.getStandardGasDensity())
                .build()
        )
        .avgTg(context.getAvgTg())
        .avgPv(context.getAvgPv())
        .avgPs(context.getAvgPs())
        .avgTm(context.getAvgTm())
        .build();
  }
}