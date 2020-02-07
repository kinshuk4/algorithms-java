package com.vaani.dsa.ds.core.graph.nongeneric.stringgraph;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

@ToString
@EqualsAndHashCode (exclude = "edges")
class Vertex {

    private final String label;
    private final Set<Edge> edges; // collection of edges to neighbors

    public Vertex(String label) {
        this.label = label;
        edges = new HashSet<>();
    }

    String getLabel() {
        return label;
    }

    boolean addEdge(Edge edge) {
        return edges.add(edge);
    }

    boolean addEdge(Vertex v2, int weight) {
        return edges.add(new Edge(this, v2, weight));
    }

    boolean contains(Edge edge) {
        return edges.contains(edge);
    }

    boolean removeEdge(Edge edge) {
        return edges.remove(edge);
    }

    List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    // TODO override hashCode() and equals()

    // Flyweight for Vertex
    private static Map<String, Vertex> vertexCache = new HashMap<>();

    public static Vertex from(String vertexLabel) {
        return vertexCache.computeIfAbsent(vertexLabel, Vertex::new);
    }
}