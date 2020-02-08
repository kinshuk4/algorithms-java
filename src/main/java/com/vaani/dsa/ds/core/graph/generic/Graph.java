package com.vaani.dsa.ds.core.graph.generic;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.NotImplementedException;

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

        Edge<T> e1 = new Edge<>(v1, v2, weight);

        if (!v1.contains(e1)) {
            v1.addEdge(e1);

            if (!this.isDirected) {
                Edge<T> e2 = new Edge<>(v2, v1, weight);
                if (!v2.contains(e2)) {
                    v2.addEdge(e2);
                }
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

    public boolean hasCycle() {
        if (isDirected){
            throw new NotImplementedException("Method not available.");
        }else{
            return hasCycleUndirectedDfs();
        }
    }

    private boolean hasCycleUndirectedDfs(){
        boolean result = false;

        //visited array
        Set<Vertex<T>> visited = new LinkedHashSet<>();
        //do DFS, from each vertex
        for (Vertex<T> v : adjVertices.values()) {
            if (!visited.contains(v)) {
                if (hasCycleUndirectedDfsUtil(v, visited, null)) {
                    return true;
                }
            }
        }
        return result;
    }

    private boolean hasCycleUndirectedDfsUtil(Vertex<T> currVertex, Set<Vertex<T>> visited, Vertex<T> parent) {

        //add this vertex to visited vertex
        visited.add(currVertex);

        //visit neighbors except its direct parent
        for (Edge<T> e : currVertex.getEdges()) {
            Vertex<T> vertex = e.getTo();
            //check the adjacent vertex from current vertex
            if (!vertex.equals(parent)) {
                //if destination vertex is not its direct parent then
                if (visited.contains(vertex)) {
                    //if here means this destination vertex is already visited
                    //means cycle has been detected
                    return true;
                } else {
                    //recursion from destination node
                    if (hasCycleUndirectedDfsUtil(vertex, visited, currVertex)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
