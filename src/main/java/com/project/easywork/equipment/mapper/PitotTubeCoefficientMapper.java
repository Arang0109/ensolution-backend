package com.project.easywork.equipment.mapper;

import com.project.easywork.equipment.domain.dto.PitotCoefficientD;
import com.project.easywork.equipment.domain.persistance.PitotTubeCoefficient;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder()
)
public interface PitotTubeCoefficientMapper {
  
  PitotCoefficientD toDto(PitotTubeCoefficient pitotTubeCoefficient);
  List<PitotCoefficientD> toDtoList(List<PitotTubeCoefficient> dtos);
}
