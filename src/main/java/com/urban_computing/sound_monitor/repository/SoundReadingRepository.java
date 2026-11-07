package com.urban_computing.sound_monitor.repository;

import com.urban_computing.sound_monitor.entity.SoundReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoundReadingRepository extends JpaRepository<SoundReading, Long> {
    List<SoundReading> findByNodeIdOrderByTimestampDesc(String nodeId);
}
