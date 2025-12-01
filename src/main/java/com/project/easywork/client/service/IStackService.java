package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.stack.StackCreateRequestDto;
import com.project.easywork.client.domain.dto.stack.StackDetailResponseDto;
import com.project.easywork.client.domain.dto.stack.StackResponseDto;
import com.project.easywork.client.domain.dto.stack.StackUpdateRequestDto;

import java.util.List;

public interface IStackService {
  void registerStack(StackCreateRequestDto dto);
  StackDetailResponseDto getStack(Long id);
  List<StackResponseDto> getStacks();
  StackResponseDto updateStack(Long id, StackUpdateRequestDto dto);
  void removeStack(Long id);
}