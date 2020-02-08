package com.vaani.dsa.ds.core.graph.nongeneric.stringgraph;

import lombok.EqualsAndHashCode;

import java.util.*;

@EqualsAndHashCode(exclude = "weight")
class StringEdge {
    private final StringVertex from;
    private final StringVertex to;

    private final int weight;
    // private final String id;

    public StringEdge(final StringVertex from, final StringVertex to, final int weight) {
        super();
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public StringEdge(final String from, final String to, final int weight) {
        super();
        this.from = StringVertex.from(from);
        this.to = StringVertex.from(to);
        this.weight = weight;
    }

    public StringEdge(final String from, final String to) {
        this(from, to, 1);
    }

    StringVertex getTo() {
        return to;
    }

    int getWeight() {
        return weight;
    }

    StringVertex getFrom() {
        return from;
    }

    boolean contains(final StringVertex v) {
        return to.equals(v) || from.equals(v);
    }

    // TODO override hashCode() and equals()

    // Flyweight for Edge
    private static Map<String, StringEdge> edgeCache = new HashMap<>();

    public static StringEdge from(String fromLabel, String toLabel, int weight) {
        String edgeLabel = fromLabel + "--" + toLabel;
        return edgeCache.computeIfAbsent(edgeLabel, newEdgeLabel -> new StringEdge(fromLabel, toLabel, weight));
    }

    public static StringEdge from(String fromLabel, String toLabel) {
        return StringEdge.from(fromLabel, toLabel, 1);
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