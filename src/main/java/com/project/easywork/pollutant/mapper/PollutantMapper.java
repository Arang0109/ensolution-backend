package com.project.easywork.pollutant.mapper;

import com.project.easywork.pollutant.domain.dto.PollutantCreateRequestDto;
import com.project.easywork.pollutant.domain.dto.PollutantResponseDto;
import com.project.easywork.pollutant.domain.dto.PollutantUpdateRequestDto;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder()
)
public interface PollutantMapper {
  Pollutant toEntityFromPollutantCreateDto(PollutantCreateRequestDto dto);
  PollutantResponseDto toDto(Pollutant pollutant);
  
  List<PollutantResponseDto> toDtoList(List<Pollutant> pollutants);
  
  @BeanMapping(
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  void updatePollutant(PollutantUpdateRequestDto dto, @MappingTarget Pollutant pollutant);
}
