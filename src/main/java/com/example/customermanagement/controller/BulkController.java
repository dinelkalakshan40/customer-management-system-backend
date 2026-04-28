package com.example.customermanagement.controller;


import com.example.customermanagement.service.BulkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/bulk")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class BulkController {
    private final BulkService service;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {
        Map<String,Object> response =service.processExcel(file);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/bulk-update")
    public ResponseEntity<String> bulkUpdate(@RequestParam("file") MultipartFile file) {
        service.updateProcessExcel(file);
        return ResponseEntity.ok("Bulk update completed");
    }
}
