package com.example.customermanagement.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface BulkService {
    Map<String, Object> processExcel(MultipartFile file);
    void updateProcessExcel(MultipartFile file);
}
