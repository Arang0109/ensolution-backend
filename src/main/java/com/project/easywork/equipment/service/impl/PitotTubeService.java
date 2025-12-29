package com.project.easywork.equipment.service.impl;

import com.project.easywork.equipment.domain.dto.PitotTubeDetailResponseDto;
import com.project.easywork.equipment.domain.dto.PitotTubeTableViewDto;
import com.project.easywork.equipment.domain.persistance.PitotTube;
import com.project.easywork.equipment.mapper.PitotTubeMapper;
import com.project.easywork.equipment.service.IPitotTubeService;
import com.project.easywork.equipment.service_data.IPitotTubeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PitotTubeService implements IPitotTubeService {
  
  private final IPitotTubeDataService pitotTubeDataService;
  private final PitotTubeMapper pitotTubeMapper;
  
  @Override
  public PitotTubeTableViewDto getList() {
    
    List<PitotTube> pitotTubeList = pitotTubeDataService.findAll();
    List<PitotTubeDetailResponseDto> pitotTubeDetailResponseDtos =
        pitotTubeMapper.toDetailDtoList(pitotTubeList);
    
    
    return PitotTubeTableViewDto.builder()
        .pitotTubeList(pitotTubeDetailResponseDtos)
        .build();
  }
}
