package com.project.easywork.pollutant.mapper;

import com.project.easywork.pollutant.domain.dto.PollutantCreateD;
import com.project.easywork.pollutant.domain.dto.PollutantD;
import com.project.easywork.pollutant.domain.dto.PollutantUpdateD;
import com.project.easywork.pollutant.domain.persistance.Pollutant;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    builder = @Builder()
)
public interface PollutantMapper {
  Pollutant toEntity(PollutantCreateD dto);
  
  PollutantD toDto(Pollutant pollutant);
  
  List<PollutantD> toDtoList(List<Pollutant> pollutants);
}
