package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.stack.StackCreateRequestDto;
import com.project.easywork.client.domain.dto.stack.StackDetailResponseDto;
import com.project.easywork.client.domain.dto.stack.StackResponseDto;
import com.project.easywork.client.domain.dto.stack.StackUpdateRequestDto;

import java.util.List;

public interface IStackService {
  void registerStack(StackCreateRequestDto requestDto);
  StackDetailResponseDto getStack(Long stackId);
  List<StackResponseDto> getStacks();
  StackResponseDto updateStack(Long stackId, StackUpdateRequestDto requestDto);
  void removeStack(Long stackId);
}