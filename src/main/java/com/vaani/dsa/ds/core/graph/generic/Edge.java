package com.vaani.dsa.ds.core.graph.generic;

//To represent the edges in the graph.
public class Edge<T> {
    private static final int DEFAULT_WEIGHT = 1;

    public Vertex<T> from, to;
    public int weight;

    public Edge(Vertex<T> from, Vertex<T> to) {
        this(from, to, DEFAULT_WEIGHT);
    }

    public Edge(Vertex from, Vertex to, int weight) {
        super();
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Edge)) return false;

        Edge _obj = (Edge) obj;
        return _obj.from.equals(from) && _obj.to.equals(to) &&
                _obj.weight == weight;
    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        result = 31 * result + weight;
        return result;
    }

    public Vertex<T> getAdjacentVertex(Vertex<T> vertexToBeProcessed) {
        if (from.equals(vertexToBeProcessed)) {
            return to;
        } else if (to.equals(vertexToBeProcessed)) {
            return from;
        } else {
            return null;
        }
    }
}
