package com.project.easywork.equipment.mapper;

import com.project.easywork.equipment.domain.dto.PitotDetailD;
import com.project.easywork.equipment.domain.dto.PitotD;
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
  
  PitotD toDto(PitotTube pitotTube);
  
  @Mapping(source = ".", target = "pitotTube")
  @Mapping(source = "pitotTubeCoefficientList", target = "coefficientList")
  PitotDetailD toDetailDto(PitotTube pitotTube);
  List<PitotDetailD> toDetailDtoList(List<PitotTube> pitotTubes);

}
