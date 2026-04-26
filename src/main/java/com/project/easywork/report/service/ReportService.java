package com.project.easywork.report.service;

import com.project.easywork.common.exception.CustomException;
import com.project.easywork.common.exception.ErrorCode;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import com.project.easywork.measurement.mapper.ClientDocMapper;
import com.project.easywork.measurement.mapper.EquipmentDocMapper;
import com.project.easywork.measurement.mapper.MeasurementDocMapper;
import com.project.easywork.measurement.mapper.MeasurementSheetDocMapper;
import com.project.easywork.measurement.service_data.IMeasurementDataService;
import com.project.easywork.report.domain.bundle.DataBundle;
import com.project.easywork.report.domain.client.ClientDataD;
import com.project.easywork.report.domain.client.PreDataD;
import com.project.easywork.report.domain.equipment.EquipmentDataD;
import com.project.easywork.report.domain.sheet.SheetDataD;
import com.project.easywork.report.domain.stack.StackDataD;
import com.project.easywork.report.excel.factory.ExistingExcelFactory;
import com.project.easywork.report.mapper.ExcelMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {
  
  private final IMeasurementDataService measurementDataService;
  private final ExistingExcelFactory existingExcelFactory;
  private final ExcelMapper excelMapper;
  
  private final MeasurementDocMapper measurementDocMapper;
  private final ClientDocMapper clientDocMapper;
  private final EquipmentDocMapper equipmentDocMapper;
  private final MeasurementSheetDocMapper measurementSheetDocMapper;
  
  public DownloadFile createReportZip(Long planId) {
    MeasurementDoc doc = measurementDataService.findByPlanId(planId);
    
    if (doc == null) {
      throw new CustomException(ErrorCode.NOT_FOUND);
    }
    
    if (CollectionUtils.isEmpty(doc.getSheets())) {
      throw new CustomException(ErrorCode.NOT_FOUND);
    }
    
    DataBundle dataBundle = mappedData(doc);
    
    try (
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(bos)
    ) {
      for (SheetDataD sheetData : dataBundle.getSheetDataList()) {
        byte[] excelBytes = createSingleExcel(dataBundle, sheetData);
        
        String fileName = String.format(
            "fKET-A-QP-17-02-01(2)_대기측정기록부(%s)_%s.xlsx",
            dataBundle.getPreData().getReferenceNumber(),
            sheetData.getCategory().getDescription()
        );
        
        ZipEntry entry = new ZipEntry(fileName);
        zos.putNextEntry(entry);
        zos.write(excelBytes);
        zos.closeEntry();
      }
      
      zos.finish();
      
      return DownloadFile.builder()
          .fileName("대기측정기록부-" + dataBundle.getPreData().getReferenceNumber() + ".zip")
          .contentType("application/zip")
          .data(bos.toByteArray())
          .build();
      
    } catch (IOException e) {
      throw new CustomException(ErrorCode.BAD_REQUEST);
    }
  }
  
  private byte[] createSingleExcel(DataBundle dataBundle, SheetDataD sheetData) throws IOException {
    try (
        Workbook workbook = existingExcelFactory.createWorkbook();
        ByteArrayOutputStream bos = new ByteArrayOutputStream()
    ) {
      excelMapper.preDataSheetMap(workbook, dataBundle, sheetData);
      excelMapper.analysisReportMap(workbook, dataBundle);
      excelMapper.measurementReportMap(workbook, sheetData);
      workbook.setForceFormulaRecalculation(true);
      
      workbook.write(bos);
      return bos.toByteArray();
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
  
  @Getter
  @Builder
  public static class DownloadFile {
    private String fileName;
    private String contentType;
    private byte[] data;
  }
}