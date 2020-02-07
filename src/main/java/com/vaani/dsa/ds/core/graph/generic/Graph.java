package com.vaani.dsa.ds.core.graph.generic;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

//https://www.geeksforgeeks.org/graph-and-its-representations/
//https://gist.github.com/smddzcy/bf8fc17dedf4d40b0a873fc44f855a58
//http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
@ToString
@EqualsAndHashCode
public class Graph<T> {
    private Map<T, Vertex<T>> adjVertices; // collection of all vertices
    private final boolean isDirected;

    public Graph(boolean isDirected) {
        adjVertices = new HashMap<>();
        this.isDirected = isDirected;
    }

    public List<Vertex<T>> getVertices() {
        return new ArrayList<>(adjVertices.values());
    }

    void addVertex(T label) {
        Vertex<T> v = new Vertex<>(label);
        addVertex(v);
    }

    void addVertex(Vertex<T> v) {
        if (!adjVertices.containsKey(v.getValue())) {
            adjVertices.put(v.getValue(), v);
        }

    }

    void removeVertex(T label) {
        Vertex<T> v = new Vertex<>(label);
        // Remove all vertices which has above vertex
        adjVertices.values()
                .forEach(v1 -> v1.getEdges().stream().filter(e -> e.contains(v)).forEach(v1::removeEdge));
        adjVertices.remove(v);
    }

    Vertex<T> vertexFrom(T v1Label) {
        return adjVertices.get(v1Label);
    }

    public void addEdge(T v1Label, T v2Label, int weight) {
        addVertex(v1Label);
        addVertex(v2Label);

        Vertex<T> v1 = vertexFrom(v1Label);
        Vertex<T> v2 = vertexFrom(v2Label);

        Edge<T> e = new Edge<>(v1, v2, weight);

        if (!v1.contains(e)) {
            v1.addEdge(e);

            if (!this.isDirected) {
                v2.addEdge(e);
            }
        }
    }

    public void addEdge(T v1Label, T v2Label) {
        addEdge(v1Label, v2Label, 1);
    }

    void removeEdge(T v1Label, T v2Label) {
        Edge<T> e = new Edge<>(adjVertices.get(v1Label), adjVertices.get(v2Label));
        adjVertices.values().forEach(v -> v.removeEdge(e));
    }

    //https://www.baeldung.com/java-graphs
    public Set<Vertex<T>> breadthFirstTraversal(T rootLabel) {
        Vertex<T> root = vertexFrom(rootLabel);
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.poll();
            for (Edge<T> e : vertex.getEdges()) {
                Vertex<T> v = e.getTo();
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }

        return visited;
    }

    //https://www.baeldung.com/java-graphs
    public Set<Vertex<T>> depthFirstTraversal(T root) {
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        Stack<Vertex<T>> stack = new Stack<>();
        Vertex<T> rootVertex = vertexFrom(root);
        stack.push(rootVertex);
        while (!stack.isEmpty()) {
            Vertex<T> vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Edge<T> e : vertex.getEdges()) {
                    Vertex<T> v = e.getTo();
                    stack.push(v);
                }
            }
        }
        return visited;
    }

    public Set<Vertex<T>> dfsRecursion(T startVertex) {
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        dfs(vertexFrom(startVertex), visited);
        return visited;
    }

    private void dfs(Vertex<T> start, Set<Vertex<T>> visited) {
        visited.add(start);

        System.out.print(start + " ");
        for (Edge<T> e : start.getEdges()) {
            Vertex<T> destination = e.getTo();
            if (!visited.contains(destination)) {
                dfs(destination, visited);
            }
        }
    }

    public List<Vertex<T>> topologicalSorting() {
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        Stack<Vertex<T>> stack = new Stack<>();
        //visit from each node if not already visited
        for (Vertex<T> v : adjVertices.values()) {
            if (!visited.contains(v)) {
                topologicalSortUtil(v, visited, stack);
            }
        }
        System.out.println("Topological Sort: ");
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.print(stack.pop() + " ");
        }

        List<Vertex<T>> result = new ArrayList<>(stack);
        Collections.reverse(result);
        return result;
    }

    private void topologicalSortUtil(Vertex<T> start, Set<Vertex<T>> visited, Stack<Vertex<T>> stack) {
        visited.add(start);

        for (Edge<T> e : start.getEdges()) {
            Vertex<T> destination = e.getTo();
            if (!visited.contains(destination)) {
                topologicalSortUtil(destination, visited, stack);
            }
        }

        stack.push(start);
    }
}
