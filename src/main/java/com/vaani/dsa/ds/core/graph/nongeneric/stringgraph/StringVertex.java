package com.vaani.dsa.ds.core.graph.nongeneric.stringgraph;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

@ToString
@EqualsAndHashCode (exclude = "edges")
class StringVertex {

    private final String label;
    private final Set<StringEdge> edges; // collection of edges to neighbors

    public StringVertex(String label) {
        this.label = label;
        edges = new HashSet<>();
    }

    String getLabel() {
        return label;
    }

    boolean addEdge(StringEdge edge) {
        return edges.add(edge);
    }

    boolean addEdge(StringVertex v2, int weight) {
        return edges.add(new StringEdge(this, v2, weight));
    }

    boolean contains(StringEdge edge) {
        return edges.contains(edge);
    }

    boolean removeEdge(StringEdge edge) {
        return edges.remove(edge);
    }

    List<StringEdge> getEdges() {
        return new ArrayList<>(edges);
    }

    // TODO override hashCode() and equals()

    // Flyweight for Vertex
    private static Map<String, StringVertex> vertexCache = new HashMap<>();

    public static StringVertex from(String vertexLabel) {
        return vertexCache.computeIfAbsent(vertexLabel, StringVertex::new);
    }
}