package com.learndatastructure.interviewcake.treesandgraphs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Sanjay Singh Rawat
 * @since 2020.10.14
 */
public class GraphNode {

    private String label;
    private Set<GraphNode> neighbors;
    private Optional<String> color;

    public GraphNode(String label) {
        this.label = label;
        neighbors = new HashSet<>();
        color = Optional.empty();
    }

    public String getLabel() {
        return label;
    }

    public Set<GraphNode> getNeighbors() {
        return Collections.unmodifiableSet(neighbors);
    }

    public void addNeighbor(GraphNode neighbor) {
        neighbors.add(neighbor);
    }

    public boolean hasColor() {
        return color.isPresent();
    }

    public String getColor() {
        return color.get();
    }

    public void setColor(String color) {
        this.color = Optional.ofNullable(color);
    }
}
