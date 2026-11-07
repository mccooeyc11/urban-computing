package com.urban_computing.sound_monitor.repository;

import com.urban_computing.sound_monitor.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NodeRepository extends JpaRepository<Node, Long> {
    Optional<Node> findByNodeId(String nodeId);
}
