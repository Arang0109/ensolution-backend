package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.PreInfoCommandD;
import com.project.easywork.measurement.dto.document.input.PreInfoDoc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PreInfoMapper {
  PreInfoDoc toDocument(PreInfoCommandD preInfo);
}
