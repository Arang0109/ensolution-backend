package com.project.easywork.equipment.domain.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.project.easywork.equipment.domain.EquipType;
import com.project.easywork.equipment.domain.dto.spec.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EquipmentUpdateReqD(
    EquipType type,
    String managementNumber,
    String serialNumber,
    String modelName,
    String equipmentName,
    String alias,
    
    @PositiveOrZero BigDecimal price,
    String manufacturer,
    String originCountry,
    @PastOrPresent LocalDate purchaseDate,
    String remark,
    
    @Positive Integer calibrationCycle,
    
    @JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type"
    )
    @JsonSubTypes({
        @JsonSubTypes.Type(value = ParticleSamplerSpecD.class, name = "PARTICLE_SAMPLER"),
        @JsonSubTypes.Type(value = GasSamplerSpecD.class, name = "GAS_SAMPLER"),
        @JsonSubTypes.Type(value = PitotTubeSpecD.class, name = "PITOT_TUBE"),
        @JsonSubTypes.Type(value = NozzleSpecD.class, name = "NOZZLE"),
        @JsonSubTypes.Type(value = OtherSpecD.class, name = "OTHER")
    })
    EquipmentSpecReqD spec
) {
}