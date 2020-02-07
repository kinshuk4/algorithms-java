package com.vaani.dsa.ds.core.graph.generic;

import lombok.EqualsAndHashCode;

//To represent the edges in the graph.
@EqualsAndHashCode(exclude = "weight")
public class Edge<T> {
    private static final int DEFAULT_WEIGHT = 1;

    public Vertex<T> from, to;
    public int weight;

    public Edge(Vertex<T> from, Vertex<T> to) {
        this(from, to, DEFAULT_WEIGHT);
    }

    public Edge(Vertex<T> from, Vertex<T> to, int weight) {
        super();
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

//    public Edge(final T from, final T to, final int weight) {
//        super();
////        this.from = Vertex.from(from);
////        this.to = Vertex.from(to);
//        this.from = new Vertex<>(from);
//        this.to = new Vertex<>(to);
//        this.weight = weight;
//    }

//    public Edge(final T from, final T to) {
//        this(from, to, DEFAULT_WEIGHT);
//    }



    public Vertex<T> getAdjacentVertex(Vertex<T> vertexToBeProcessed) {
        if (from.equals(vertexToBeProcessed)) {
            return to;
        } else if (to.equals(vertexToBeProcessed)) {
            return from;
        } else {
            return null;
        }
    }

    Vertex<T> getTo() {
        return to;
    }

    int getWeight() {
        return weight;
    }

    Vertex<T> getFrom() {
        return from;
    }

    boolean contains(final Vertex<T> v) {
        return to.equals(v) || from.equals(v);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from.getValue() +
                ", to=" + to.getValue() +
                ", weight=" + weight +
                '}';
    }
}
