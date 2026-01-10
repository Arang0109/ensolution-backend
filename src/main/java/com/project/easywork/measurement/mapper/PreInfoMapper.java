package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.PreInfoCommandDto;
import com.project.easywork.measurement.dto.document.input.PreInfoDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PreInfoMapper {
  PreInfoDocument toDocument(PreInfoCommandDto preInfo);
}
