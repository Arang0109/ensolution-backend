package com.project.easywork.report.mapper;

import com.project.easywork.client.domain.Shape;
import com.project.easywork.report.domain.client.ClientDataD;
import com.project.easywork.report.domain.client.PreDataD;
import com.project.easywork.report.domain.bundle.DataBundle;
import com.project.easywork.report.domain.equipment.EquipmentDataD;
import com.project.easywork.report.domain.sheet.*;
import com.project.easywork.report.domain.stack.StackDataD;
import com.project.easywork.report.excel.ReportCellWriter;
import com.project.easywork.report.excel.ReportSheetNames;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExcelMapper {
  
  private final ReportCellWriter cellWriter;
  
  public void preDataSheetMap(Workbook workbook, DataBundle data, SheetDataD sheetData) {
    Sheet sheet = workbook.getSheet(ReportSheetNames.PRE_DATA);
    
    PreDataD preData = data.getPreData();
    
    cellWriter.write(sheet, 3, 7, spaced(preData.getMentor())); // H4
    cellWriter.write(sheet, 3, 10, spaced(preData.getMentee())); // K4
    
    String ref = String.format("01-%s-%s", preData.getReferenceNumber(), sheetData.getReferenceNumber());
    
    cellWriter.write(sheet, 3, 14, ref);
    cellWriter.write(sheet, 3, 15, sheetData.getCategory().getDescription());
    
    // 채취일 (N5)
    cellWriter.write(sheet, 4, 13, preData.getMeasureDate());
    
//    String[] startTime = data.getMeasureStartTime().toString().split(":");
//    String[] endTime = doc.getMeasureEndTime().toString().split(":");
//
//    cellWriter.write(sheet, 5, 7, Integer.parseInt(startTime[0]));
//    cellWriter.write(sheet, 5, 8, Integer.parseInt(startTime[1]));
//    cellWriter.write(sheet, 5, 10, Integer.parseInt(endTime[0]));
//    cellWriter.write(sheet, 5, 11, Integer.parseInt(endTime[1]));
    
    WeatherDataD weather = sheetData.getWeather();

    cellWriter.write(sheet, 7, 7, weather.getAtmPressure());
    cellWriter.write(sheet, 7, 9, weather.getWeatherCondition().getDescription());
    cellWriter.write(sheet, 7, 11, nullSafeFloat(weather.getTemperature()));
    cellWriter.write(sheet, 7, 13, nullSafeFloat(weather.getHumidity()));
    cellWriter.write(sheet, 7, 14, weather.getWindDirection().getDescription());
    cellWriter.write(
        sheet,
        7,
        15,
        (weather.getWindSpeed() == null || weather.getWindSpeed().equals(BigDecimal.ZERO))
            ? "-"
            : weather.getWindSpeed()
    );
    
    // 1. 연소가스 분석기 Parts.
    ExhaustGasDataD exhaustGas = sheetData.getExhaustGas();
    
    cellWriter.write(
      sheet, 12, 17,
      data.getStackData().getStandardOxygen() != null ? data.getStackData().getStandardOxygen() : 20.9);

    cellWriter.write(sheet, 12, 7, nullSafeFloat(exhaustGas.getO2Concentration().getFirst()));
    cellWriter.write(sheet, 12, 9, nullSafeFloat(exhaustGas.getO2Concentration().get(1)));
    cellWriter.write(sheet, 12, 11, nullSafeFloat(exhaustGas.getO2Concentration().getLast()));

    cellWriter.write(sheet, 14, 7, nullSafeFloat(exhaustGas.getCo2Concentration().getFirst()));
    cellWriter.write(sheet, 14, 9, nullSafeFloat(exhaustGas.getCo2Concentration().get(1)));
    cellWriter.write(sheet, 14, 11, nullSafeFloat(exhaustGas.getCo2Concentration().getLast()));

    cellWriter.write(sheet, 16, 7, nullSafeFloat(exhaustGas.getCoConcentration().getFirst()));
    cellWriter.write(sheet, 16, 9, nullSafeFloat(exhaustGas.getCoConcentration().get(1)));
    cellWriter.write(sheet, 16, 11, nullSafeFloat(exhaustGas.getCoConcentration().getLast()));
    
    // 2. 측정점 산정 Parts.
    StackDataD stack = data.getStackData();
    Shape shape = stack.getShape();
    if (shape.equals(Shape.CIRCULAR)) {
      cellWriter.write(sheet, 30, 3, nullSafeFloat(stack.getHorizontalLength()));
    }
    
    if (shape.equals(Shape.RECTANGULAR)) {
      cellWriter.write(sheet, 32, 3, nullSafeFloat(stack.getHorizontalLength()));
      cellWriter.write(sheet, 32, 4, nullSafeFloat(stack.getVerticalLength()));
    }
    
    // 3. 수분량 Parts.
    MoistureDataD moisture = sheetData.getMoisture();

    cellWriter.write(sheet, 47, 7, nullSafeFloat(moisture.getBeforeW()));
    cellWriter.write(sheet, 48, 7, nullSafeFloat(moisture.getAfterW()));

    cellWriter.write(sheet, 50, 7, nullSafeFloat(moisture.getInTemp()));
    cellWriter.write(sheet, 51, 7, nullSafeFloat(moisture.getOutTemp()));

    cellWriter.write(sheet, 53, 7, nullSafeFloat(moisture.getSuctionVelocity()));
    cellWriter.write(sheet, 54, 19, nullSafeFloat(moisture.getGasMeterGaugePressure()));

    cellWriter.write(sheet, 55, 7, nullSafeFloat(moisture.getBeforeV()));
    cellWriter.write(sheet, 56, 7, nullSafeFloat(moisture.getAfterV()));

//    // 4. 등속흡인계수 Parts.
    EquipmentDataD equipment = data.getEquipmentData();
    
    cellWriter.write(sheet, 62, 7, nullSafeFloat(equipment.getDeltaH()));
    cellWriter.write(sheet, 63, 7, nullSafeFloat(equipment.getYd()));
    cellWriter.write(sheet, 64, 7, nullSafeFloat(sheetData.getCp()));

    List<MeasurementPointDataD> mps = sheetData.getMeasurementPoints();

    for (int i = 0; i < mps.size(); i++) {
      var mp = mps.get(i);
      int col = (2 * i) + 7;
      cellWriter.write(sheet, 68, col, nullSafeFloat(mp.getTs()));
      cellWriter.write(sheet, 69, col, nullSafeFloat(mp.getPv()));
      cellWriter.write(sheet, 70, col, nullSafeFloat(mp.getPs()));
      cellWriter.write(sheet, 71, col, nullSafeFloat(mp.getInTemp()));
      cellWriter.write(sheet, 72, col, nullSafeFloat(mp.getOutTemp()));

      cellWriter.write(sheet, 74, col, nullSafeFloat(mp.getSamplingTime()));

      cellWriter.write(sheet, 77, col, nullSafeFloat(mp.getBeforeV()));
      cellWriter.write(sheet, 78, col, nullSafeFloat(mp.getAfterV()));

      cellWriter.write(sheet, 83, col, nullSafeFloat(sheetData.getNozzleSize()));

      cellWriter.write(sheet, 87, col, nullSafeFloat(mp.getVacuumGaugePressure()));
      cellWriter.write(sheet, 88, col, nullSafeFloat(mp.getFinalImpingerTemperature()));
    }
    
    // 이후 여기에 계속 추가
    // cellWriter.write(sheet, row, col, value);
  }
  
  public void analysisReportMap(Workbook workbook, DataBundle data, SheetDataD sheetData) {
    Sheet sheet = workbook.getSheet(ReportSheetNames.ANALYSIS_REPORT);
    ClientDataD client = data.getClientData();
    StackDataD stack = data.getStackData();
    
    cellWriter.write(sheet, 3, 4, client.getWorkplaceName());
    cellWriter.write(sheet, 4, 4, client.getAddress());
    cellWriter.write(sheet, 5, 4, spaced(client.getCeoName()));
    cellWriter.write(sheet, 5, 11, client.getBusinessCategory());
    cellWriter.write(sheet, 6, 11, client.getManager());
    
    String stackName = stack.getSemsNumber() + "(" + stack.getName() + ")";
    cellWriter.write(sheet, 8, 4, stackName);
    cellWriter.write(sheet, 9, 4, client.getGrade().getNumberString());
    cellWriter.write(sheet, 10, 4, stack.getGrade().getNumberString());
    
    String height = stack.getHeight().toString() + " (측정공)";
    cellWriter.write(sheet, 10, 7, height);
    
    cellWriter.write(sheet, 12, 4, stack.getPreventions().getFirst().getName());
    
  }
  
  private String spaced(String str) {
    if (str == null) return "";
    return String.join(" ", str.split(""));
  }
  
  private BigDecimal nullSafeFloat(BigDecimal value) {
    return value != null ? value : BigDecimal.valueOf(0.0);
  }
}