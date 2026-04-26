package com.project.easywork.report.mapper;

import com.project.easywork.client.domain.Shape;
import com.project.easywork.plan.domain.MeasurementCategory;
import com.project.easywork.report.domain.bundle.DataBundle;
import com.project.easywork.report.domain.client.ClientDataD;
import com.project.easywork.report.domain.client.PreDataD;
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
import java.time.LocalTime;
import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class ExcelMapper {
  
  private final ReportCellWriter cellWriter;
  
  public void preDataSheetMap(Workbook workbook, DataBundle data, SheetDataD sheetData) {
    Sheet sheet = workbook.getSheet(ReportSheetNames.PRE_DATA);
    
    if (sheet == null || data == null || sheetData == null) return;
    
    PreDataD preData = data.getPreData();
    WeatherDataD weather = sheetData.getWeather();
    ExhaustGasDataD exhaustGas = sheetData.getExhaustGas();
    StackDataD stack = data.getStackData();
    MoistureDataD moisture = sheetData.getMoisture();
    EquipmentDataD equipment = data.getEquipmentData();
    List<MeasurementPointDataD> mps = sheetData.getMeasurementPoints();
    
    cellWriter.write(sheet, 3, 7, spaced(get(preData::getMentor)));   // H4
    cellWriter.write(sheet, 3, 10, spaced(get(preData::getMentee)));  // K4
    
    String ref = buildReferenceNumber(preData, sheetData);
    cellWriter.write(sheet, 3, 14, ref);
    cellWriter.write(sheet, 3, 15, get(() -> sheetData.getCategory().getDescription()));
    
    // 채취일
    cellWriter.write(sheet, 4, 13, get(preData::getMeasureDate));
    
    writeTime(sheet, 5, 7, get(preData::getMeasureStartTime));
    writeTime(sheet, 5, 10, get(preData::getMeasureEndTime));
    
    // 날씨
    cellWriter.write(sheet, 7, 7, get(weather::getAtmPressure));
    cellWriter.write(sheet, 7, 9, get(() -> weather.getWeatherCondition().getDescription()));
    cellWriter.write(sheet, 7, 11, get(weather::getTemperature));
    cellWriter.write(sheet, 7, 13, get(weather::getHumidity));
    cellWriter.write(sheet, 7, 14, get(() -> weather.getWindDirection().getDescription()));
    cellWriter.write(sheet, 7, 15, formatWindSpeed(get(weather::getWindSpeed)));
    
    // 연소가스 분석기
    cellWriter.write(sheet, 12, 17, getOrDefault(() -> data.getStackData().getStandardOxygen(), BigDecimal.valueOf(20.9)));
    
    cellWriter.write(sheet, 12, 7, getListValue(exhaustGas::getO2Concentration, 0));
    cellWriter.write(sheet, 12, 9, getListValue(exhaustGas::getO2Concentration, 1));
    cellWriter.write(sheet, 12, 11, getListValue(exhaustGas::getO2Concentration, 2));
    
    cellWriter.write(sheet, 14, 7, getListValue(exhaustGas::getCo2Concentration, 0));
    cellWriter.write(sheet, 14, 9, getListValue(exhaustGas::getCo2Concentration, 1));
    cellWriter.write(sheet, 14, 11, getListValue(exhaustGas::getCo2Concentration, 2));
    
    cellWriter.write(sheet, 16, 7, getListValue(exhaustGas::getCoConcentration, 0));
    cellWriter.write(sheet, 16, 9, getListValue(exhaustGas::getCoConcentration, 1));
    cellWriter.write(sheet, 16, 11, getListValue(exhaustGas::getCoConcentration, 2));
    
    // 측정점 산정
    Shape shape = get(stack::getShape);
    
    if (Shape.CIRCULAR.equals(shape)) {
      cellWriter.write(sheet, 30, 3, get(stack::getHorizontalLength));
    }
    
    if (Shape.RECTANGULAR.equals(shape)) {
      cellWriter.write(sheet, 32, 3, get(stack::getHorizontalLength));
      cellWriter.write(sheet, 32, 4, get(stack::getVerticalLength));
    }
    
    // 수분량
    cellWriter.write(sheet, 47, 7, get(moisture::getBeforeW));
    cellWriter.write(sheet, 48, 7, get(moisture::getAfterW));
    cellWriter.write(sheet, 50, 7, get(moisture::getInTemp));
    cellWriter.write(sheet, 51, 7, get(moisture::getOutTemp));
    cellWriter.write(sheet, 53, 7, get(moisture::getSuctionVelocity));
    cellWriter.write(sheet, 54, 19, get(moisture::getGasMeterGaugePressure));
    cellWriter.write(sheet, 55, 7, get(moisture::getBeforeV));
    cellWriter.write(sheet, 56, 7, get(moisture::getAfterV));
    
    // 등속흡인계수
    cellWriter.write(sheet, 62, 7, get(equipment::getDeltaH));
    cellWriter.write(sheet, 63, 7, get(equipment::getYd));
    cellWriter.write(sheet, 64, 7, get(sheetData::getCp));
    
    if (mps != null) {
      for (int i = 0; i < mps.size(); i++) {
        MeasurementPointDataD mp = mps.get(i);
        if (mp == null) continue;
        
        int col = (2 * i) + 7;
        
        cellWriter.write(sheet, 68, col, get(mp::getTs));
        cellWriter.write(sheet, 69, col, get(mp::getPv));
        cellWriter.write(sheet, 70, col, get(mp::getPs));
        cellWriter.write(sheet, 71, col, get(mp::getInTemp));
        cellWriter.write(sheet, 72, col, get(mp::getOutTemp));
        cellWriter.write(sheet, 74, col, get(mp::getSamplingTime));
        cellWriter.write(sheet, 77, col, get(mp::getBeforeV));
        cellWriter.write(sheet, 78, col, get(mp::getAfterV));
        cellWriter.write(sheet, 83, col, get(sheetData::getNozzleSize));
        cellWriter.write(sheet, 87, col, get(mp::getVacuumGaugePressure));
        cellWriter.write(sheet, 88, col, get(mp::getFinalImpingerTemperature));
      }
    }
  }
  
  public void analysisReportMap(Workbook workbook, DataBundle data) {
    Sheet sheet = workbook.getSheet(ReportSheetNames.ANALYSIS_REPORT);
    
    if (sheet == null || data == null) return;
    
    ClientDataD client = data.getClientData();
    StackDataD stack = data.getStackData();
    
    cellWriter.write(sheet, 3, 4, get(client::getWorkplaceName));
    cellWriter.write(sheet, 4, 4, get(client::getAddress));
    cellWriter.write(sheet, 5, 4, spaced(get(client::getCeoName)));
    cellWriter.write(sheet, 5, 11, get(client::getBusinessCategory));
    cellWriter.write(sheet, 6, 11, get(client::getManager));
    
    String stackName = buildStackName(stack);
    cellWriter.write(sheet, 8, 4, stackName);
    cellWriter.write(sheet, 9, 4, get(() -> client.getGrade().getNumberString()));
    cellWriter.write(sheet, 10, 4, get(() -> stack.getGrade().getNumberString()));
    cellWriter.write(sheet, 10, 7, buildHeightText(stack));
    cellWriter.write(sheet, 12, 4, getFirstPreventionName(stack));
  }
  
  public void measurementReportMap(Workbook workbook, SheetDataD sheetData) {
    Sheet sheet = workbook.getSheet(ReportSheetNames.MEASUREMENT_REPORT);
    
    if (sheet == null || sheetData == null) return;
    
    ExhaustGasDataD gas = sheetData.getExhaustGas();
    
    LocalTime gasStart = get(gas::getGasAnalyzerStartTime);
    writeTime(sheet, 21, 24, gasStart);
    writeTime(sheet, 21, 26, plusMinutesSafe(gasStart, 15));
    
    LocalTime thcStart = get(gas::getThcAnalyzerStartTime);
    writeTime(sheet, 21, 28, thcStart);
    writeTime(sheet, 21, 30, plusMinutesSafe(thcStart, 30));
    
    MeasurementCategory category = sheetData.getCategory();
    if (category == null) return;
    
    if (category != MeasurementCategory.GAS) {
      ParticleSampleD particleSample = sheetData.getParticleSample();
      
      cellWriter.write(sheet, 1, 26, get(particleSample::getThimbleFilter));
      cellWriter.write(sheet, 3, 26, get(particleSample::getBgThimbleFilter));
      
      switch (category) {
        case DUST -> {
          writeTime(sheet, 18, 24, get(particleSample::getSamplingStartTime));
          writeTime(sheet, 18, 26, get(particleSample::getSamplingEndTime));
        }
        case HEAVY_METAL -> {
          writeTime(sheet, 18, 28, get(particleSample::getSamplingStartTime));
          writeTime(sheet, 18, 30, get(particleSample::getSamplingEndTime));
        }
        case MERCURY -> {
          writeTime(sheet, 18, 32, get(particleSample::getSamplingStartTime));
          writeTime(sheet, 18, 34, get(particleSample::getSamplingEndTime));
        }
      }
    }
  }
  
  private <T> T get(Supplier<T> supplier) {
    try {
      return supplier.get();
    } catch (Exception e) {
      return null;
    }
  }
  
  private <T> T getOrDefault(Supplier<T> supplier, T defaultValue) {
    T value = get(supplier);
    return value != null ? value : defaultValue;
  }
  
  private BigDecimal getListValue(Supplier<List<BigDecimal>> supplier, int index) {
    List<BigDecimal> list = get(supplier);
    if (list == null || list.size() <= index) return null;
    return list.get(index);
  }
  
  private String buildReferenceNumber(PreDataD preData, SheetDataD sheetData) {
    String preRef = get(preData::getReferenceNumber);
    
    if (preRef == null) return null;
    
    return String.format("01-%s", preRef);
  }
  
  private String buildStackName(StackDataD stack) {
    String semsNumber = get(stack::getSemsNumber);
    String name = get(stack::getName);
    
    if (semsNumber == null && name == null) return null;
    if (semsNumber == null) return name;
    if (name == null) return semsNumber;
    
    return semsNumber + "(" + name + ")";
  }
  
  private String buildHeightText(StackDataD stack) {
    BigDecimal height = get(stack::getHeight);
    return height != null ? height + " (측정공)" : null;
  }
  
  private String getFirstPreventionName(StackDataD stack) {
    List<?> preventions = get(stack::getPreventions);
    if (preventions == null || preventions.isEmpty()) return null;
    
    Object first = preventions.getFirst();
    try {
      return (String) first.getClass().getMethod("getName").invoke(first);
    } catch (Exception e) {
      return null;
    }
  }
  
  private Object formatWindSpeed(BigDecimal windSpeed) {
    if (windSpeed == null || BigDecimal.ZERO.compareTo(windSpeed) == 0) {
      return "-";
    }
    return windSpeed;
  }
  
  private String spaced(String str) {
    if (str == null || str.isBlank()) return null;
    return String.join(" ", str.split(""));
  }
  
  private LocalTime plusMinutesSafe(LocalTime time, long minutes) {
    return time != null ? time.plusMinutes(minutes) : null;
  }
  
  private void writeTime(Sheet sheet, int row, int col, LocalTime time) {
    if (sheet == null || time == null) return;
    
    cellWriter.write(sheet, row, col, time.getHour());
    cellWriter.write(sheet, row, col + 1, time.getMinute());
  }
}