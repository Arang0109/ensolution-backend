package com.project.easywork.measurement.mapper;

import com.project.easywork.measurement.dto.command.StackInfoCommandD;
import com.project.easywork.measurement.dto.document.input.ClientDoc;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StackInfoMapper {
  ClientDoc.StackDoc toDocument(StackInfoCommandD dto);
}

