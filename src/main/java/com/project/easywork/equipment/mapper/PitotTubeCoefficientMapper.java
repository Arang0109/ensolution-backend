package com.project.easywork.equipment.mapper;

import com.project.easywork.equipment.domain.dto.PitotTubeCoefficientResponseDto;
import com.project.easywork.equipment.domain.persistance.PitotTubeCoefficient;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder()
)
public interface PitotTubeCoefficientMapper {
  
  PitotTubeCoefficientResponseDto toDto(PitotTubeCoefficient pitotTubeCoefficient);
  List<PitotTubeCoefficientResponseDto> toDtoList(List<PitotTubeCoefficient> dtos);
}
