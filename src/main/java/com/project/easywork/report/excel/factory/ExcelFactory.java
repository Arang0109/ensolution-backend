package com.project.easywork.report.excel.factory;

import org.apache.poi.ss.usermodel.Workbook;

abstract class ExcelFactory {
  abstract Workbook createWorkbook();
}