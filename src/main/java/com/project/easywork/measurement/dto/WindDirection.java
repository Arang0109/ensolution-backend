package com.project.easywork.measurement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WindDirection {
  
  CALM("정온"),
  
  N("북"),
  NNE("북북동"),
  NE("북동"),
  ENE("동북동"),
  
  E("동"),
  ESE("동남동"),
  SE("남동"),
  SSE("남남동"),
  
  S("남"),
  SSW("남남서"),
  SW("남서"),
  WSW("서남서"),
  
  W("서"),
  WNW("서북서"),
  NW("북서"),
  NNW("북북서");
  
  private final String description;
}