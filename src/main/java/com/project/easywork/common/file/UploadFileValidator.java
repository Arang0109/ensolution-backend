package com.project.easywork.common.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UploadFileValidator {
  
  private final FileTypeValidator fileTypeValidator;
  
  private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
  
  public void validate(MultipartFile file) {
    if (file == null || file.isEmpty()) {
      throw new IllegalArgumentException("업로드된 파일이 없습니다.");
    }
    
    if (file.getSize() > MAX_FILE_SIZE) {
      throw new IllegalArgumentException("파일 크기는 5MB를 초과할 수 없습니다.");
    }
    
    fileTypeValidator.validate(file);
  }
}