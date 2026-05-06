package com.project.easywork.common.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileReader<T> {
  List<T> read(MultipartFile file);
}