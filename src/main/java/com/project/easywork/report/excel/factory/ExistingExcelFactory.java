package com.project.easywork.report.excel.factory;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class ExistingExcelFactory extends ExcelFactory {
  
  public Workbook createWorkbook() {
    try {
      InputStream is = getClass()
          .getClassLoader()
          .getResourceAsStream("templates/report.xlsx");
      
      if (is == null) {
        throw new IllegalArgumentException("templates/report.xlsx 파일을 찾을 수 없습니다.");
      }
      
      return new XSSFWorkbook(is);
    } catch (Exception e) {
      throw new IllegalStateException("엑셀 템플릿 로드 실패", e);
    }
  }
}