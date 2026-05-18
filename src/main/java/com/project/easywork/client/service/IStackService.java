package com.project.easywork.client.service;

import com.project.easywork.client.domain.dto.stack.StackCreateD;
import com.project.easywork.client.domain.dto.stack.StackD;
import com.project.easywork.client.domain.dto.stack.StackDetailD;
import com.project.easywork.client.domain.dto.stack.StackUpdateD;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IStackService {
  StackD registerStack(StackCreateD dto);
  StackDetailD getStack(Long id);
  List<StackD> getStacks();
  StackD updateStack(Long id, StackUpdateD dto);
  void removeStack(Long id);
  
  void importStacks(MultipartFile file);
}