package com.project.easywork.measurement.dto.snapshot.client;

public record CompanySnapshot(
    Long companyId,
    String name,
    String ceoName
) {
}