package com.project.easywork.measurement.dto.snapshot;

import com.project.easywork.agency.domain.entity.Team;
import com.project.easywork.client.domain.persistance.*;
import com.project.easywork.user.domain.entity.User;

import java.util.List;

public record MeasurementSnapshot(
    Company company,
    Workplace workplace,
    Stack stack,
    List<Prevention> preventions,
    List<StackMeasurement> measurements,
    Team team,
    User senior,
    User junior,
    String vehicleNumber
) {}