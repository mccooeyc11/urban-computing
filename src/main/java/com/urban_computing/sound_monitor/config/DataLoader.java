package com.urban_computing.sound_monitor.config;

import com.urban_computing.sound_monitor.entity.Node;
import com.urban_computing.sound_monitor.repository.NodeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final NodeRepository nodeRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(NodeRepository nodeRepository, ObjectMapper objectMapper) {
        this.nodeRepository = nodeRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (nodeRepository.count() > 0) return;

        InputStream is = getClass().getResourceAsStream("/nodes.json");
        if (is == null) {
            throw new IllegalStateException("nodes.json not found");
        }

        Node[] nodes = objectMapper.readValue(is, Node[].class);
        nodeRepository.saveAll(Arrays.asList(nodes));
    }
}
