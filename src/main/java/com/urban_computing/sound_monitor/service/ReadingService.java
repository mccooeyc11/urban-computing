package com.urban_computing.sound_monitor.service;

import com.urban_computing.sound_monitor.dto.ReadingDisplayDto;
import com.urban_computing.sound_monitor.entity.Node;
import com.urban_computing.sound_monitor.entity.SoundReading;
import com.urban_computing.sound_monitor.repository.NodeRepository;
import com.urban_computing.sound_monitor.repository.SoundReadingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadingService {

    private final SoundReadingRepository soundReadingRepository;
    private final NodeRepository nodeRepository;

    public ReadingService(SoundReadingRepository soundReadingRepository, NodeRepository nodeRepository) {
        this.soundReadingRepository = soundReadingRepository;
        this.nodeRepository = nodeRepository;
    }

    public List<ReadingDisplayDto> getAllReadings() {
        List<SoundReading> readings = soundReadingRepository.findAll();

        return readings.stream().map(this::toDto).toList();
    }

    public List<ReadingDisplayDto> getReadingsByNode(String nodeId) {
        List<SoundReading> readings = soundReadingRepository.findByNodeIdOrderByTimestampDesc(nodeId);
        return readings.stream().map(this::toDto).toList();
    }

    private ReadingDisplayDto toDto(SoundReading reading) {
        Optional<Node> nodeOpt = nodeRepository.findByNodeId(reading.getNodeId());

        String displayName = nodeOpt.map(Node::getDisplayName).orElse("Unknown");
        String description = nodeOpt.map(Node::getDescription).orElse("Unknown");

        return new ReadingDisplayDto(
                reading.getNodeId(),
                displayName,
                description,
                reading.getTimestamp(),
                reading.getSoundLevelDb(),
                reading.getSourceFileName()
        );
    }
}