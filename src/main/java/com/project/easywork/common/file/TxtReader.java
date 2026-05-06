package com.project.easywork.common.file;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class TxtReader {
  
  public List<String> read(MultipartFile file) {
    List<String> lines = new ArrayList<>();
    
    try (
        BufferedReader br = new BufferedReader(
            new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)
        )
    ) {
      String line;
      
      while ((line = br.readLine()) != null) {
        if (line.isBlank()) continue;
        
        lines.add(line);
      }
      
      return lines;
      
    } catch (Exception e) {
      throw new IllegalArgumentException("TXT 파일을 읽는 중 오류가 발생했습니다.", e);
    }
  }
}