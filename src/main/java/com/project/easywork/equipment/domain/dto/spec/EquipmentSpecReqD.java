package com.project.easywork.equipment.domain.dto.spec;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ParticleSamplerSpecD.class, name = "PARTICLE_SAMPLER"),
    @JsonSubTypes.Type(value = GasSamplerSpecD.class, name = "GAS_SAMPLER"),
    @JsonSubTypes.Type(value = PitotTubeSpecD.class, name = "PITOT_TUBE"),
    @JsonSubTypes.Type(value = NozzleSpecD.class, name = "NOZZLE"),
    @JsonSubTypes.Type(value = OtherSpecD.class, name = "OTHER"),
        })
public interface EquipmentSpecReqD {}