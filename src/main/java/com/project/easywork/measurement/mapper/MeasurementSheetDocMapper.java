package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.domain.document.sheets.*;
import com.project.easywork.report.domain.sheet.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MeasurementSheetDocMapper {
  
  @Mapping(target = "Cp", source = "particleSample.cp")
  @Mapping(target = "nozzleSize", source = "particleSample.nozzleSize")
  SheetDataD toSheetDataDto(MeasurementSheetDoc doc);
  
  List<SheetDataD> toSheetDataListDto(List<MeasurementSheetDoc> docs);
  
  @Mapping(target = "atmPressure", source = "pressure.pressure")
  WeatherDataD toWeatherDataDto(WeatherDoc doc);
  
  @Mapping(target = "beforeW", source = "weight.before")
  @Mapping(target = "afterW", source = "weight.after")
  @Mapping(target = "beforeV", source = "dryGasVolume.before")
  @Mapping(target = "afterV", source = "dryGasVolume.after")
  @Mapping(target = "inTemp", source = "gasMeterTemperature.in")
  @Mapping(target = "outTemp", source = "gasMeterTemperature.out")
  MoistureDataD toMoistureDataDto(MoistureDoc doc);
  
  ExhaustGasDataD toExhaustGasDataDto(ExhaustGasDoc doc);
  
  @Mapping(target = "Ts", source = "ts")
  @Mapping(target = "Pv", source = "pv")
  @Mapping(target = "Ps", source = "ps")
  @Mapping(target = "beforeV", source = "equipmentVolume.beforeVm")
  @Mapping(target = "afterV", source = "equipmentVolume.afterVm")
  @Mapping(target = "inTemp", source = "equipmentTemperature.inTm")
  @Mapping(target = "outTemp", source = "equipmentTemperature.outTm")
  MeasurementPointDataD toMeasurementPointDataDto(MeasurementPointDoc doc);
  
  ParticleSampleD toParticleSampleDto(ParticleSampleDoc doc);
  
  List<MeasurementPointDataD> toMeasurementPointDataListDto(List<MeasurementPointDoc> docs);
}
