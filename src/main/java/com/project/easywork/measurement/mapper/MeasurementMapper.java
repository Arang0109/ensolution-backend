package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.MeasurementCommandD;
import com.project.easywork.measurement.dto.document.MeasurementDoc;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {
        StackInfoMapper.class,
        WeatherMapper.class,
        MoistureMapper.class,
        EquipmentDocMapper.class,
        ExhaustGasMapper.class
    }
)
public interface MeasurementMapper {
  MeasurementDoc toDocument(MeasurementCommandD dto);
}