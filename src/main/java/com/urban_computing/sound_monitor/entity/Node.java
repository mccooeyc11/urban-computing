package com.urban_computing.sound_monitor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "nodes")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @JsonProperty("canonical")
    private @Getter @Setter String nodeId;

    @JsonProperty("name")
    private @Getter @Setter String displayName;

    private @Getter @Setter @Lob String description;

    public Node() {
    }

    public Node(String nodeId, String location, String description) {
        this.nodeId = nodeId;
        this.displayName = location;
        this.description = description;
    }
}
