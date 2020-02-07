package com.vaani.dsa.ds.core.graph.generic;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

@ToString
@EqualsAndHashCode(exclude = "edges")
public class Vertex<T> {
    public T value;
    private final Set<Edge<T>> edges; // collection of edges to neighbors

    public Vertex(T label) {
        this.value = label;
        edges = new HashSet<>();
    }

    public T getValue() {
        return value;
    }

    boolean addEdge(Edge<T> edge) {
        if (!edges.contains(edge)){
            return edges.add(edge);
        }
        return false;
    }

    boolean addEdge(Vertex<T> v2, int weight) {
        return edges.add(new Edge<T>(this, v2, weight));
    }

    boolean contains(Edge<T> edge) {
        return edges.contains(edge);
    }

    boolean removeEdge(Edge<T> edge) {
        return edges.remove(edge);
    }

    List<Edge<T>> getEdges() {
        return new ArrayList<>(edges);
    }

    // TODO override hashCode() and equals()

//    // Flyweight for Vertex
//    private static Map<String, Vertex<T>> vertexCache = new HashMap<>();
//
//    private static Vertex<?> from(String vertexLabel) {
//        return vertexCache.computeIfAbsent(vertexLabel, Vertex::new);
//    }
//
//    public static Vertex<?> from(Object vertexLabel) {
//        return vertexCache.computeIfAbsent(vertexLabel.toString(), Vertex::new);
//    }



    public enum Color {
        WHITE, GRAY, BLACK
    }
}