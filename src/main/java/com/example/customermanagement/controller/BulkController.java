package com.example.customermanagement.controller;


import com.example.customermanagement.service.BulkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/bulk")
@RequiredArgsConstructor
public class BulkController {
    private final BulkService service;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        service.processExcel(file);
        return ResponseEntity.ok("Upload started");
    }
    @PutMapping("/bulk-update")
    public ResponseEntity<String> bulkUpdate(@RequestParam("file") MultipartFile file) {
        service.updateProcessExcel(file);
        return ResponseEntity.ok("Bulk update completed");
    }
}
