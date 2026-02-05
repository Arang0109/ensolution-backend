package com.project.easywork.equipment.domain.document.spec;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.project.easywork.equipment.domain.EquipType;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ParticleSamplerSpec.class, name = "PARTICLE_SAMPLER"),
    @JsonSubTypes.Type(value = GasSamplerSpec.class, name = "GAS_SAMPLER"),
    @JsonSubTypes.Type(value = PitotTubeSpec.class, name = "PITOT_TUBE"),
    @JsonSubTypes.Type(value = NozzleSpec.class, name = "NOZZLE"),
    @JsonSubTypes.Type(value = OtherSpec.class, name = "OTHER")
})
public abstract class EquipmentSpec {
  public abstract EquipType getType();
}