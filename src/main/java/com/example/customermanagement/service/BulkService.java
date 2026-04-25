package com.example.customermanagement.service;

import org.springframework.web.multipart.MultipartFile;

public interface BulkService {
    void processExcel(MultipartFile file);
}
