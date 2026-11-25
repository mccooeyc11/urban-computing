package com.urban_computing.sound_monitor.dto;

import java.time.LocalDateTime;

public class ReadingDisplayDto {
    private String nodeId;
    private String displayName;
    private String description;
    private LocalDateTime timestamp;
    private Double soundLevelDb;
    private String sourceFileName;

    public ReadingDisplayDto(String nodeId, String displayName, String description,
                             LocalDateTime timestamp, Double soundLevelDb, String sourceFileName) {
        this.nodeId = nodeId;
        this.displayName = displayName;
        this.description = description;
        this.timestamp = timestamp;
        this.soundLevelDb = soundLevelDb;
        this.sourceFileName = sourceFileName;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Double getSoundLevelDb() {
        return soundLevelDb;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }
}
