package com.project.easywork.equipment.domain;

import lombok.Getter;

@Getter
public enum EquipStatus {
  ACTIVE, // 사용 가능
  INACTIVE, // 사용 중지
  DELETED, // 점검, 수리, 보정
  MAINTENANCE // 장비 삭제
}