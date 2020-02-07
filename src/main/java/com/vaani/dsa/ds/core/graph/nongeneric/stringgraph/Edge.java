package com.vaani.dsa.ds.core.graph.nongeneric.stringgraph;

import lombok.EqualsAndHashCode;

import java.util.*;

@EqualsAndHashCode(exclude = "weight")
class Edge {
    private final Vertex from;
    private final Vertex to;

    private final int weight;
    // private final String id;

    public Edge(final Vertex from, final Vertex to, final int weight) {
        super();
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Edge(final String from, final String to, final int weight) {
        super();
        this.from = Vertex.from(from);
        this.to = Vertex.from(to);
        this.weight = weight;
    }

    public Edge(final String from, final String to) {
        this(from, to, 1);
    }

    Vertex getTo() {
        return to;
    }

    int getWeight() {
        return weight;
    }

    Vertex getFrom() {
        return from;
    }

    boolean contains(final Vertex v) {
        return to.equals(v) || from.equals(v);
    }

    // TODO override hashCode() and equals()

    // Flyweight for Edge
    private static Map<String, Edge> edgeCache = new HashMap<>();

    public static Edge from(String fromLabel, String toLabel, int weight) {
        String edgeLabel = fromLabel + "--" + toLabel;
        return edgeCache.computeIfAbsent(edgeLabel, newEdgeLabel -> new Edge(fromLabel, toLabel, weight));
    }

    public static Edge from(String fromLabel, String toLabel) {
        return Edge.from(fromLabel, toLabel, 1);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from.getLabel() +
                ", to=" + to.getLabel() +
                ", weight=" + weight +
                '}';
    }
}