package com.vaani.dsa.ds.core.graph.nongeneric.stringgraph;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

@ToString
@EqualsAndHashCode
class Graph {

    private Set<Vertex> adjVertices; // collection of all verices
    private final boolean isDirected;

    public Graph(boolean isDirected) {
        adjVertices = new HashSet<>();
        this.isDirected = isDirected;
    }

    List<Vertex> getVertices() {
        return new ArrayList<>(adjVertices);
    }

    boolean addVertex(String label) {
        return adjVertices.add(Vertex.from(label));
    }

    void removeVertex(String label) {
        Vertex v = Vertex.from(label);
        // Remove all vertices which has above vertex
        adjVertices.stream()
                .forEach(v1 -> v1.getEdges().stream().filter(e -> e.contains(v)).forEach(e -> v1.removeEdge(e)));
        adjVertices.remove(Vertex.from(label));
    }

    void addEdge(String v1Label, String v2Label, int weight) {
        addVertex(v1Label);
        addVertex(v2Label);

        Edge e = Edge.from(v1Label, v2Label, weight);

        Vertex.from(v1Label).addEdge(e);

        if (!this.isDirected) {
            Vertex.from(v2Label).addEdge(e);
        }

    }

    void addEdge(String v1Label, String v2Label) {
        addEdge(v1Label, v2Label, 1);
    }

    void removeEdge(String v1Label, String v2Label) {
        Edge e = Edge.from(v1Label, v2Label);
        adjVertices.stream().forEach(v -> v.removeEdge(e));
    }

    //https://www.baeldung.com/java-graphs
    Set<Vertex> breadthFirstTraversal(String rootLabel) {
        Vertex root = Vertex.from(rootLabel);
        Set<Vertex> visited = new LinkedHashSet<Vertex>();
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            for (Edge e : vertex.getEdges()) {
                Vertex v = e.getTo();
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }

        return visited;
    }

    //https://www.baeldung.com/java-graphs
    Set<Vertex> depthFirstTraversal(String root) {
        Set<Vertex> visited = new LinkedHashSet<>();
        Stack<Vertex> stack = new Stack<>();
        stack.push(Vertex.from(root));
        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Edge e : vertex.getEdges()) {
                    Vertex v = e.getTo();
                    stack.push(v);
                }
            }
        }
        return visited;
    }

    public Set<Vertex> dfsRecursion(String startVertex){
        Set<Vertex> visited = new LinkedHashSet<>();
        dfs(Vertex.from(startVertex), visited);
        return visited;
    }

    private void dfs(Vertex start, Set<Vertex> visited){
        visited.add(start);

        System.out.print(start + " ");
        for (Edge e : start.getEdges()){
            Vertex destination = e.getTo();
            if (!visited.contains(destination)){
                dfs(destination, visited);
            }
        }
    }

    public List<Vertex> topologicalSorting() {
        Set<Vertex> visited = new LinkedHashSet<>();
        Stack<Vertex> stack = new Stack<>();
        //visit from each node if not already visited
        for (Vertex v: adjVertices) {
            if (!visited.contains(v)) {
                topologicalSortUtil(v, visited, stack);
            }
        }
        System.out.println("Topological Sort: ");
        int size = stack.size();
        for (int i = 0; i <size ; i++) {
            System.out.print(stack.pop() + " ");
        }

        List<Vertex> result = new ArrayList<>(stack);
        Collections.reverse(result);
        return  result;
    }

    private void topologicalSortUtil(Vertex start, Set<Vertex> visited, Stack<Vertex> stack) {
        visited.add(start);

        for (Edge e : start.getEdges()){
            Vertex destination = e.getTo();
            if (!visited.contains(destination)){
                topologicalSortUtil(destination, visited, stack);
            }
        }

        stack.push(start);
    }
}