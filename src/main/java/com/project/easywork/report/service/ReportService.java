package com.project.easywork.report.service;

import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.mapper.ClientDocMapper;
import com.project.easywork.measurement.mapper.EquipmentDocMapper;
import com.project.easywork.measurement.mapper.MeasurementDocMapper;
import com.project.easywork.measurement.mapper.MeasurementSheetDocMapper;
import com.project.easywork.measurement.service_data.IMeasurementDataService;
import com.project.easywork.report.domain.client.ClientDataD;
import com.project.easywork.report.domain.client.PreDataD;
import com.project.easywork.report.domain.equipment.EquipmentDataD;
import com.project.easywork.report.domain.sheet.SheetDataD;
import com.project.easywork.report.domain.stack.StackDataD;
import com.project.easywork.report.domain.bundle.DataBundle;
import com.project.easywork.report.excel.factory.ExistingExcelFactory;
import com.project.easywork.report.mapper.ExcelMapper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {
  
  private final IMeasurementDataService measurementDataService;
  private final ExistingExcelFactory existingExcelFactory;
  private final ExcelMapper excelMapper;
  
  private final MeasurementDocMapper measurementDocMapper;
  private final ClientDocMapper clientDocMapper;
  private final EquipmentDocMapper equipmentDocMapper;
  private final MeasurementSheetDocMapper measurementSheetDocMapper;
  
  public void createReport(Long planId) {
    MeasurementDoc doc = measurementDataService.findByPlanId(planId);
    
    DataBundle dataBundle = mappedData(doc);
    
    if (CollectionUtils.isEmpty(doc.getSheets())) throw new CustomException(ErrorCode.NOT_FOUND);
    
    try {
      for (var sheetData : dataBundle.getSheetDataList()) {
        try (Workbook workbook = existingExcelFactory.createWorkbook()) {
          
          excelMapper.preDataSheetMap(workbook, dataBundle, sheetData);
          excelMapper.analysisReportMap(workbook, dataBundle);
          excelMapper.measurementReportMap(workbook, sheetData);
          workbook.setForceFormulaRecalculation(true);
          
          String fileName = String.format(
              "fKET-A-QP-17-02-01(2) 대기측정기록부(%s-%s) %s.xlsx",
              dataBundle.getPreData().getReferenceNumber(),
              sheetData.getReferenceNumber(),
              sheetData.getCategory().getDescription());
          
          try (FileOutputStream fos = new FileOutputStream(fileName)) {
            workbook.write(fos);
          }
        }
      }
    } catch (IOException e) {
      throw new CustomException(ErrorCode.NOT_FOUND);
    } catch (Exception e) {
      throw e;
    }
  }
  
  private DataBundle mappedData(MeasurementDoc doc) {
    PreDataD preData = measurementDocMapper.toPreDataDto(doc);
    ClientDataD clientData = clientDocMapper.toClientDataDto(doc.getClient().getCompany());
    StackDataD stackData = clientDocMapper.toStackDataDto(doc.getClient().getStack());
    EquipmentDataD equipmentData = equipmentDocMapper.toEquipmentDataDto(doc.getEquipment());
    List<SheetDataD> sheetDataList = measurementSheetDocMapper.toSheetDataListDto(doc.getSheets());
    
    return DataBundle.builder()
        .preData(preData)
        .clientData(clientData)
        .stackData(stackData)
        .equipmentData(equipmentData)
        .sheetDataList(sheetDataList)
        .build();
  }
}
