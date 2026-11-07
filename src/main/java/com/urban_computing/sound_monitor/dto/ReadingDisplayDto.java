package com.urban_computing.sound_monitor.dto;

import java.time.LocalDateTime;

public class ReadingDisplayDto {
    private String nodeId;
    private String location;
    private String deviceName;
    private LocalDateTime timestamp;
    private Double soundLevelDb;
    private String sourceFileName;

    public ReadingDisplayDto(String nodeId, String location, String deviceName,
                             LocalDateTime timestamp, Double soundLevelDb, String sourceFileName) {
        this.nodeId = nodeId;
        this.location = location;
        this.deviceName = deviceName;
        this.timestamp = timestamp;
        this.soundLevelDb = soundLevelDb;
        this.sourceFileName = sourceFileName;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getLocation() {
        return location;
    }

    public String getDeviceName() {
        return deviceName;
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
