package com.project.easywork.common.excel;

import com.project.easywork.common.excel.dto.MeasurementReportExportDto;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;

public abstract class ExcelReportExporter {
  
  public final byte[] export(MeasurementReportExportDto dto) throws Exception {
    Workbook workbook = createWorkbook(dto);
    applyTemplate(workbook, dto);
    
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      workbook.write(out);
      workbook.close();
      return out.toByteArray();
    }
  }
  
  public final String getFileName(MeasurementReportExportDto dto) {
    return dto.getStackDetail().getStackName() + "-" + dto.getWorkplaceProfile().getWorkplaceName() + ".xlsx";
  }
  
  // 하위 클래스가 구현해야 하는 부분
  protected abstract Workbook createWorkbook(MeasurementReportExportDto dto) throws Exception;
  protected abstract void applyTemplate(Workbook workbook, MeasurementReportExportDto dto);
}