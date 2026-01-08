package com.project.easywork.plan.mapper;

import com.project.easywork.client.mapper.StackMeasurementMapper;
import com.project.easywork.plan.domain.dto.MeasurementItemsCreateD;
import com.project.easywork.plan.domain.dto.PlanMeasurementsD;
import com.project.easywork.plan.domain.persistance.PlanMeasurement;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
        StackMeasurementMapper.class
    }
)
public interface PlanMeasurementMapper {
  
  @Mapping(source = "stackMeasurementId", target = "stackMeasurement.id")
  @Mapping(source = "planId", target = "plan.id")
  PlanMeasurement toEntity(MeasurementItemsCreateD dto);
  
  @Mapping(target = "planId", source = "plan.id")
  @Mapping(target = "stackMeasurement", source = "stackMeasurement")
  PlanMeasurementsD toDto(PlanMeasurement planMeasurement);
  
  List<PlanMeasurementsD> toDtoList(List<PlanMeasurement> planMeasurements);
  
  List<PlanMeasurement> toEntityList(
      List<MeasurementItemsCreateD> dtos
  );
}