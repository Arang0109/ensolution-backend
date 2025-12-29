package com.project.easywork.equipment.mapper;

import com.project.easywork.equipment.domain.dto.PitotTubeDetailResponseDto;
import com.project.easywork.equipment.domain.dto.PitotTubeResponseDto;
import com.project.easywork.equipment.domain.persistance.PitotTube;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder(),
    uses = {
        PitotTubeCoefficientMapper.class
    }
)
public interface PitotTubeMapper {
  
  PitotTubeResponseDto toDto(PitotTube pitotTube);
  
  @Mapping(source = ".", target = "pitotTube")
  @Mapping(source = "pitotTubeCoefficientList", target = "coefficientList")
  PitotTubeDetailResponseDto toDetailDto(PitotTube pitotTube);
  List<PitotTubeDetailResponseDto> toDetailDtoList(List<PitotTube> pitotTubes);

}
