package com.project.easywork.report.excel.factory;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
public class AlternativeExcelFactory extends ExcelFactory {
  
  public Workbook createWorkbook() {
    return null;
  }
}