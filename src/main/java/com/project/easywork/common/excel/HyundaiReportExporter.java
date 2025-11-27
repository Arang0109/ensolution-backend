//package com.project.easywork.common.excel;
//
//import com.project.easywork.common.excel.dto.MeasurementReportExportDto;
//import com.project.easywork.client.domain.dto.facility.FacilityDto;
//import com.project.easywork.client.domain.dto.target.TargetDto;
//import com.project.easywork.client.domain.dto.prevention.PreventionDetailDto;
//import com.project.easywork.client.domain.dto.stack.StackDetailDto;
//import lombok.RequiredArgsConstructor;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//
//import java.io.*;
//import java.text.DecimalFormat;
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class HyundaiReportExporter extends ExcelReportExporter {
//  @Override
//  protected Workbook createWorkbook(MeasurementReportExportDto dto) throws Exception {
//    InputStream local = new ClassPathResource("excel/reportTemplate.xlsx").getInputStream();
//    return new XSSFWorkbook(local);
//  }
//
//  @Override
//  protected void applyTemplate(Workbook workbook, MeasurementReportExportDto dto) {
//    Sheet sheet = workbook.getSheet("대기측정 분석결과서");
//    Sheet sheet2 = workbook.getSheet("대기측정기록부(현대차)");
//
//    // 업소명
//    String workplaceName = dto.getWorkplaceProfile().getWorkplaceName();
//    sheet.getRow(3).getCell(4).setCellValue(workplaceName);
//
//    // 소재지
//    String address = dto.getWorkplaceProfile().getAddress();
//    sheet.getRow(4).getCell(4).setCellValue(address);
//
//    // 대표자
//    String ceoName = dto.getWorkplaceProfile().getCompany().getCeoName();
//    sheet.getRow(5).getCell(4).setCellValue(ceoName);
//
//    // 배출시설 관리인 & 시료채취 입회자
//    String managerName = dto.getManagerName();
//    sheet.getRow(6).getCell(4).setCellValue(managerName);
//    sheet.getRow(6).getCell(11).setCellValue(managerName);
//
//    // 시설별
//    String stackName = dto.getStackDetail().getStackName();
//    String semsNumber = dto.getStackDetail().getSemsNumber();
//    sheet.getRow(8).getCell(4).setCellValue(semsNumber + "(" + stackName + ")");
//
//    // 사업장 & 배출구 종별
//    int workplaceSizeNum = dto.getWorkplaceProfile().getWorkplaceGrade().getNumber();
//    int stackSizeNum = dto.getStackDetail().getStackGrade().getNumber();
//    sheet.getRow(9).getCell(4).setCellValue(workplaceSizeNum);
//    sheet.getRow(10).getCell(4).setCellValue(stackSizeNum);
//
//    // 측정공 높이
//    Double stackHeightValue = dto.getStackDetail().getStackHeight();
//
//    DecimalFormat df = new DecimalFormat("#.###");
//    String stackHeight = (stackHeightValue != null ? df.format(stackHeightValue) + " (측정공)" : "-");
//
//    sheet.getRow(10).getCell(7).setCellValue(stackHeight);
//
//    // 배출시설 종류
//    List<FacilityDto> allFacilities = Optional.ofNullable(dto.getStackDetail())
//        .map(StackDetailDto::getPreventions)
//        .orElse(Collections.emptyList()) // null-safe
//        .stream()
//        .filter(Objects::nonNull)
//        .flatMap(prevention -> Optional.ofNullable(prevention.getFacilities())
//            .orElse(Collections.emptyList())
//            .stream())
//        .toList();
//
//    sheet.getRow(11).getCell(4).setCellValue(allFacilities.getFirst().getFacilityName());
//
//    // 배출시설 [대기측정기록부[현대차]
//    int length2 = Math.min(allFacilities.size(), 4);
//
//    for (int i = 0; i < length2; i++) {
//      FacilityDto f = allFacilities.get(i);
//      int row = 24 + i;
//
//      String fuelUsage = safe(f.getFuelUsage());
//      String itemOutput = safe(f.getItemOutput());
//      String fuelInput  = safe(f.getFuelInput());
//      String fuelType   = safe(f.getFuelType());
//
//      String[] parts = fuelInput.split("\\s+"); // 공백 1개 이상 기준 분리
//      String value = parts.length > 0 ? parts[0] : "-";
//      String unit = parts.length > 1 ? parts[1] : "-";
//
//      sheet2.getRow(row).getCell(1).setCellValue(f.getFacilityName());
//      sheet2.getRow(row).getCell(5).setCellValue(fuelUsage);
//      sheet2.getRow(row).getCell(7).setCellValue(itemOutput);
//      sheet2.getRow(row).getCell(11).setCellValue(value);
//      sheet2.getRow(row).getCell(13).setCellValue(fuelType);
//      sheet2.getRow(row).getCell(15).setCellValue(unit);
//    }
//
//    // 방지시설 종류
//    List<PreventionDetailDto> preventions = dto.getStackDetail().getPreventions();
//    String preventionNames = preventions.stream()
//        .map(PreventionDetailDto::getPreventionName) // 각 항목의 이름 추출
//        .filter(Objects::nonNull)                    // null 방지
//        .collect(Collectors.joining(","));
//    sheet.getRow(12).getCell(4).setCellValue(preventionNames);
//
//    // 방지시설 [대기측정기록부(현대차)]
//    int length = Math.min(preventions.size(), 3);
//
//    for (int i = 0; i < length; i++) {
//      PreventionDetailDto p = preventions.get(i);
//      int row = i + 17;
//      sheet2.getRow(row).getCell(5).setCellValue(p.getPreventionName());
//
//      if (p.getTargets() != null && !p.getTargets().isEmpty()) {
//        String targetNames = p.getTargets().stream()
//            .map(TargetDto::getTargetSubstance)
//            .collect(Collectors.joining(","));
//
//        double min = Integer.MAX_VALUE;
//        double max = Integer.MIN_VALUE;
//
//        for (TargetDto t : p.getTargets()) {
//          Double eff = t.getRemovalEfficiency();
//          if (eff != null) {
//            if (eff < min) min = eff;
//            if (eff > max) max = eff;
//          }
//        }
//
//        sheet2.getRow(row).getCell(12).setCellValue(targetNames);
//        if (min == max) {
//          sheet2.getRow(row).getCell(17).setCellValue(df.format(min));
//        } else {
//          sheet2.getRow(row).getCell(17).setCellValue(df.format(min) + "-" + df.format(max));
//        }
//      }
//    }
//
//
//    // 업종
//    String businessCategory = dto.getWorkplaceProfile().getBusinessCategory();
//    sheet.getRow(5).getCell(11).setCellValue(businessCategory);
//
//    // 주생산 품목
//    // DB Field 추가하기 => workplace에 추가하면 될 듯.
//
//
//    workbook.setForceFormulaRecalculation(true);
//  }
//
//  private static String safe(String value) {
//    return (value == null || value.isBlank()) ? "-" : value;
//  }
//}
