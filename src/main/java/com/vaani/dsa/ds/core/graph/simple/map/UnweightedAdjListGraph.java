package com.vaani.dsa.ds.core.graph.simple.map;

import java.util.*;

public class UnweightedAdjListGraph {
    private Map<Integer, List<Integer>> adjMap = new LinkedHashMap<>();
    private int numVertices;
    private final boolean isDirected;

    public UnweightedAdjListGraph(int n, boolean isDirected) {
        numVertices = n;
        for (int i = 0; i < n; i++) {
            adjMap.put(i, new ArrayList<>());
        }
        this.isDirected = isDirected;
    }

    public Optional<List<Integer>> getAdjList(int vertex){
        return Optional.ofNullable(adjMap.get(vertex));
    }

    public boolean addEdge(int i, int j) {
        adjMap.putIfAbsent(i, new ArrayList<>());
        adjMap.get(i).add(j);
        if (!isDirected) {
            adjMap.putIfAbsent(j, new ArrayList<>());
            adjMap.get(j).add(i);
        }
        return true;
    }

    public boolean hasCycle() {
        int[] visited = new int[numVertices];
        for (int i = 0; i < numVertices; ++i)
            if (visited[i] == 0) {
                if (hasCycleUtil(i, visited)) {
                    return true;
                }
            }

        return false;
    }

    private boolean hasCycleUtil(int v, int[] visited) {
        if (visited[v] == 1) {
            return true;
        }
        if (visited[v] == 2) {
            return false;
        }

        visited[v] = 1;   //Mark current as visited
        for (int adjNode : adjMap.get(v)) {
            if (hasCycleUtil(adjNode, visited))
                return true;
        }

        visited[v] = 2;   //Mark current node as processed
        return false;
    }
}
