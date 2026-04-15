package com.project.easywork.measurement.dto;

import com.project.easywork.client.domain.Cycle;
import com.project.easywork.measurement.dto.document.input.*;
import com.project.easywork.plan.domain.MeasurementField;
import com.project.easywork.pollutant.domain.Method;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Schema(description = "측정 데이터 임시저장 요청 DTO")
public record SaveDraftCommandD(
  String referenceNumber,
  LocalDate measureDate,
  LocalDate receivedDate,
  LocalDate analysisDate,
  MeasurementField measurementField,
  String measurementType,
  String teamName,
  String vehicleNumber,
  String mentor,
  String mentee,
  
  ClientDoc client,
  
  String particleSamplerId,
  String gasSamplerId,
  String pitotTubeId,
  String nozzleId,
  
  List<MeasurementItemPatchCommandD> measurementItems,
  
  List<MeasurementSheetDoc> sheets,
  
  LocalTime measureStartTime,
  LocalTime measureEndTime
) {
  public record MeasurementItemPatchCommandD (
    Long stackMeasurementId,
    Long pollutantId,
    String pollutantNameKr,
    String pollutantNameEn,
    Method method,
    String testEquipment,
    String testMethod,
    Double samplingTime,
    String samplingVolume,
    Cycle cycle,
    BigDecimal allowance,
    
    LocalTime startTime,
    LocalTime endTime,
    BigDecimal suctionQuantity,
    BigDecimal gasMeterGaugePressure,
    BigDecimal inTemperature,
    BigDecimal outTemperature,
    BigDecimal beforeVolume,
    BigDecimal afterVolume,
    String blankSampleNumber,
    String sampleNumber
  ) {}
}