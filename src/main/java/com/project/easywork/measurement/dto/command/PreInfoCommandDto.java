package com.project.easywork.measurement.dto.command;

import java.time.LocalDate;
import java.util.List;

public record PreInfoCommandDto(
    LocalDate measureDate,
    String measurementType,
    String teamName,
    String vehicleNumber,
    List<String> engineers,
    
    String particularEquipmentName,
    String pitotTubeName
) {}