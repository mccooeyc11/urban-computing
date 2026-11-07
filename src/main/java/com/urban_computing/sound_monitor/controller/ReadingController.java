package com.urban_computing.sound_monitor.controller;

import com.urban_computing.sound_monitor.dto.ReadingDisplayDto;
import com.urban_computing.sound_monitor.service.ExcelImportService;
import com.urban_computing.sound_monitor.service.ReadingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/readings")
public class ReadingController {

    private final ExcelImportService excelImportService;
    private final ReadingService readingService;

    public ReadingController(ExcelImportService excelImportService, ReadingService readingService) {
        this.excelImportService = excelImportService;
        this.readingService = readingService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("nodeId") String nodeId) throws IOException {

        excelImportService.importFile(file, nodeId);
        return ResponseEntity.ok("File processed successfully");
    }

    @GetMapping
    public List<ReadingDisplayDto> getAll() {
        return readingService.getAllReadings();
    }

    @GetMapping("/node/{nodeId}")
    public List<ReadingDisplayDto> getByNode(@PathVariable String nodeId) {
        return readingService.getReadingsByNode(nodeId);
    }
}