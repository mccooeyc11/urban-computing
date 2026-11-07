package com.urban_computing.sound_monitor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "nodes")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private @Getter @Setter String nodeId;

    private @Getter @Setter String location;

    private @Getter @Setter String deviceName;

    public Node() {
    }

    public Node(String nodeId, String location, String deviceName) {
        this.nodeId = nodeId;
        this.location = location;
        this.deviceName = deviceName;
    }
}
