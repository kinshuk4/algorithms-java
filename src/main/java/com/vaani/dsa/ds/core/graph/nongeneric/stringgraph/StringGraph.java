package com.vaani.dsa.ds.core.graph.nongeneric.stringgraph;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.*;

@ToString
@EqualsAndHashCode
class StringGraph {

    private Set<StringVertex> adjVertices; // collection of all vertices
    private final boolean isDirected;

    public StringGraph(boolean isDirected) {
        adjVertices = new HashSet<>();
        this.isDirected = isDirected;
    }

    List<StringVertex> getVertices() {
        return new ArrayList<>(adjVertices);
    }

    boolean addVertex(String label) {
        return adjVertices.add(StringVertex.from(label));
    }

    void removeVertex(String label) {
        StringVertex v = StringVertex.from(label);
        // Remove all vertices which has above vertex
        adjVertices.stream()
                .forEach(v1 -> v1.getEdges().stream().filter(e -> e.contains(v)).forEach(e -> v1.removeEdge(e)));
        adjVertices.remove(StringVertex.from(label));
    }

    void addEdge(String v1Label, String v2Label, int weight) {
        addVertex(v1Label);
        addVertex(v2Label);

        StringEdge e = StringEdge.from(v1Label, v2Label, weight);

        StringVertex.from(v1Label).addEdge(e);

        if (!this.isDirected) {
            StringVertex.from(v2Label).addEdge(e);
        }

    }

    void addEdge(String v1Label, String v2Label) {
        addEdge(v1Label, v2Label, 1);
    }

    void removeEdge(String v1Label, String v2Label) {
        StringEdge e = StringEdge.from(v1Label, v2Label);
        adjVertices.stream().forEach(v -> v.removeEdge(e));
    }

    //https://www.baeldung.com/java-graphs
    Set<StringVertex> breadthFirstTraversal(String rootLabel) {
        StringVertex root = StringVertex.from(rootLabel);
        Set<StringVertex> visited = new LinkedHashSet<StringVertex>();
        Queue<StringVertex> queue = new LinkedList<StringVertex>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            StringVertex vertex = queue.poll();
            for (StringEdge e : vertex.getEdges()) {
                StringVertex v = e.getTo();
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }

        return visited;
    }

    //https://www.baeldung.com/java-graphs
    Set<StringVertex> depthFirstTraversal(String root) {
        Set<StringVertex> visited = new LinkedHashSet<>();
        Stack<StringVertex> stack = new Stack<>();
        stack.push(StringVertex.from(root));
        while (!stack.isEmpty()) {
            StringVertex vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (StringEdge e : vertex.getEdges()) {
                    StringVertex v = e.getTo();
                    stack.push(v);
                }
            }
        }
        return visited;
    }

    public Set<StringVertex> dfsRecursion(String startVertex){
        Set<StringVertex> visited = new LinkedHashSet<>();
        dfs(StringVertex.from(startVertex), visited);
        return visited;
    }

    private void dfs(StringVertex start, Set<StringVertex> visited){
        visited.add(start);

        System.out.print(start + " ");
        for (StringEdge e : start.getEdges()){
            StringVertex destination = e.getTo();
            if (!visited.contains(destination)){
                dfs(destination, visited);
            }
        }
    }

    public List<StringVertex> topologicalSorting() {
        Set<StringVertex> visited = new LinkedHashSet<>();
        Stack<StringVertex> stack = new Stack<>();
        //visit from each node if not already visited
        for (StringVertex v: adjVertices) {
            if (!visited.contains(v)) {
                topologicalSortUtil(v, visited, stack);
            }
        }
        System.out.println("Topological Sort: ");
        int size = stack.size();
        for (int i = 0; i <size ; i++) {
            System.out.print(stack.pop() + " ");
        }

        List<StringVertex> result = new ArrayList<>(stack);
        Collections.reverse(result);
        return  result;
    }

    private void topologicalSortUtil(StringVertex start, Set<StringVertex> visited, Stack<StringVertex> stack) {
        visited.add(start);

        for (StringEdge e : start.getEdges()){
            StringVertex destination = e.getTo();
            if (!visited.contains(destination)){
                topologicalSortUtil(destination, visited, stack);
            }
        }

        stack.push(start);
    }
}