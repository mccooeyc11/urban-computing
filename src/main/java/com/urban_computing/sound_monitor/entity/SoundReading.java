package com.urban_computing.sound_monitor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Entity
@Table(name = "sound_readings")
public class SoundReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter @Setter Long id;

    private @Getter @Setter String nodeId;

    private @Getter @Setter LocalDateTime timestamp;

    private @Getter @Setter Double soundLevelDb;

    private @Getter @Setter String sourceFileName;

    public SoundReading() {
    }

    public SoundReading(String nodeId, LocalDateTime timestamp, Double soundLevelDb, String sourceFileName) {
        this.nodeId = nodeId;
        this.timestamp = timestamp;
        this.soundLevelDb = soundLevelDb;
        this.sourceFileName = sourceFileName;
    }
}
