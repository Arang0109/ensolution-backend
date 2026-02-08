package com.project.easywork.equipment.domain;

import lombok.Getter;

@Getter
public enum EquipStatus {
  ACTIVE, // 사용 가능
  INACTIVE, // 사용 중지
  MAINTENANCE, // 점검, 수리, 보정
  DELETED // 장비 삭제
}