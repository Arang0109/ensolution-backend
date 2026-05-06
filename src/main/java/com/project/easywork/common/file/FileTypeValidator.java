package com.project.easywork.common.file;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Component
public class FileTypeValidator {
  
  private static final Set<String> ALLOWED_EXTENSIONS = Set.of("csv", "txt");
  
  public void validate(MultipartFile file) {
    String filename = file.getOriginalFilename();
    
    if (filename == null || !filename.contains(".")) {
      throw new IllegalArgumentException("파일 확장자가 없습니다.");
    }
    
    String extension = getExtension(filename);
    
    if (!ALLOWED_EXTENSIONS.contains(extension)) {
      throw new IllegalArgumentException("지원하지 않는 파일 형식입니다: " + extension);
    }
  }
  
  private String getExtension(String filename) {
    return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
  }
}