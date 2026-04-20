package com.project.easywork.report.excel;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReportCellWriter {
  
  private CellStyle dateStyle;
  
  public void write(Sheet sheet, int rowIndex, int colIndex, Object value) {
    Row row = sheet.getRow(rowIndex);
    if (row == null) row = sheet.createRow(rowIndex);
    
    Cell cell = row.getCell(colIndex);
    if (cell == null) cell = row.createCell(colIndex);
    
    if (value == null) {
      cell.setBlank();
      return;
    }
    
    if (value instanceof LocalDate v) {
      writeDate(cell, v);
      return;
    }
    
    if (value instanceof String v) {
      cell.setCellValue(v);
    } else if (value instanceof Number v) {
      cell.setCellValue(v.doubleValue());
    } else if (value instanceof Boolean v) {
      cell.setCellValue(v);
    } else {
      cell.setCellValue(String.valueOf(value));
    }
  }
  
  private void writeDate(Cell cell, LocalDate date) {
    Workbook workbook = cell.getSheet().getWorkbook();
    
    cell.setCellValue(java.sql.Date.valueOf(date));
    
    CellStyle originStyle = cell.getCellStyle();
    
    CellStyle newStyle = workbook.createCellStyle();
    newStyle.cloneStyleFrom(originStyle); // 기존 스타일 복사
    
    CreationHelper helper = workbook.getCreationHelper();
    newStyle.setDataFormat(
        helper.createDataFormat().getFormat("m\"월\" d\"일\"")
    );
    
    cell.setCellStyle(newStyle);
  }
}