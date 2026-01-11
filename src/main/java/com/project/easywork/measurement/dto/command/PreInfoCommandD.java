package com.project.easywork.measurement.dto.command;

import java.time.LocalDate;
import java.util.List;

public record PreInfoCommandD(
    LocalDate measureDate,
    String measurementType,
    String teamName,
    String vehicleNumber,
    List<String> engineers,
    
    boolean simplifiedMeasurement
) {}