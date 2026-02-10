package com.project.easywork.common;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.easywork.equipment.domain.EquipType;
import lombok.*;

public class Test {
  
  public static void main(String[] args) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    
    Equipment equipment = new Equipment(
        EquipType.PITOT_TUBE,
        "피토우관",
        "관이다.",
        new Pitot("속도")
    );
    
    String json = objectMapper.writeValueAsString(equipment);
    System.out.println("직렬화 후");
    System.out.println(json);
    
    Equipment read = objectMapper.readValue(json, Equipment.class);
    
    System.out.println(read.getSpec());
  }
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
class Equipment {
  private EquipType type;
  private String name;
  private String description;
  
  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
      property = "type"
  )
  @JsonSubTypes({
      @JsonSubTypes.Type(value = Pitot.class, name = "PITOT_TUBE"),
      @JsonSubTypes.Type(value = Nozzle.class, name = "NOZZLE")
  })
  private EquipSpec spec;
}

interface EquipSpec {
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Pitot implements EquipSpec {
  private String velocity;
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Nozzle implements EquipSpec {
  private String diameter;
}