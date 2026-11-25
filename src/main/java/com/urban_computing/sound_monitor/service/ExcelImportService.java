package com.urban_computing.sound_monitor.service;


import com.urban_computing.sound_monitor.entity.SoundReading;
import com.urban_computing.sound_monitor.repository.NodeRepository;
import com.urban_computing.sound_monitor.repository.SoundReadingRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelImportService {

    private final SoundReadingRepository soundReadingRepository;
    private final NodeRepository nodeRepository;

    public ExcelImportService(SoundReadingRepository soundReadingRepository, NodeRepository nodeRepository) {
        this.soundReadingRepository = soundReadingRepository;
        this.nodeRepository = nodeRepository;
    }

    @Transactional
    public void importFile(MultipartFile file, String nodeId) throws IOException {
        if (nodeRepository.findByNodeId(nodeId).isEmpty()) {
            throw new IllegalArgumentException("Unknown nodeId: " + nodeId);
        }

        List<SoundReading> readings = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 2; i <= sheet.getLastRowNum(); i++) { // skip header rows
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell timestampCell = row.getCell(0);
                Cell soundLevelCell = row.getCell(1);

                if (timestampCell == null || soundLevelCell == null) continue;

                LocalDateTime timestamp = readTimestamp(timestampCell);
                Double soundLevel = readDouble(soundLevelCell);

                if (timestamp == null || soundLevel == null) continue;

                SoundReading reading = new SoundReading(
                        nodeId,
                        timestamp,
                        soundLevel,
                        file.getOriginalFilename()
                );

                readings.add(reading);
            }
        }

        soundReadingRepository.saveAll(readings);
    }

    private LocalDateTime readTimestamp(Cell cell) {
        try {
            if (cell.getCellType() == CellType.STRING) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd", Locale.ENGLISH);
                return LocalDateTime.parse(cell.getStringCellValue().trim(), fmt);
            }
            if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                return cell.getLocalDateTimeCellValue();
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private Double readDouble(Cell cell) {
        try {
            if (cell.getCellType() == CellType.NUMERIC) {
                return cell.getNumericCellValue();
            }
            if (cell.getCellType() == CellType.STRING) {
                return Double.parseDouble(cell.getStringCellValue());
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}
