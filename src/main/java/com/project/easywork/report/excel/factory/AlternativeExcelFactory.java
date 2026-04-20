package com.project.easywork.report.excel.factory;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class AlternativeExcelFactory extends ExcelFactory {
  
  public Workbook createWorkbook() {
    return null;
  }
}