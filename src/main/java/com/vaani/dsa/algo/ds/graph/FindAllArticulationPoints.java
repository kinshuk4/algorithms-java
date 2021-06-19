package com.vaani.dsa.algo.ds.graph;

import com.vaani.dsa.ds.core.graph.simple.list.Graph;

import java.util.*;

public class FindAllArticulationPoints {
    int time = 0;

    public List<Integer> findArticulationPoints(Graph graph) {
        List<Integer> articulationPoints = new ArrayList<>();
        int vertices = graph.vertices;

        Map<Integer, Integer> visitTime = new HashMap<>();
        Map<Integer, Integer> lowTime = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        //created visited array
        boolean[] visited = new boolean[vertices];

        //this loop will handle disconnected graphs as well
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(graph, i, visitTime, lowTime, visited, parent, articulationPoints);
            }
        }

        return articulationPoints;

    }

    public void dfs(Graph graph, int u, Map<Integer, Integer> discoveryTime, Map<Integer, Integer> lowTime, boolean[] visited, Map<Integer, Integer> parent, List<Integer> articulationPoints) {
        LinkedList<Integer> adjList[] = graph.adjList;
        int childCount = 0;
        boolean canArticulationPoint = false;
        visited[u] = true;
        discoveryTime.put(u, time);
        lowTime.put(u, time);
        time++;
        for (int v : adjList[u]) {

            if (!visited[v]) {
                //make current vertex as parent of adjacent vertex
                // and increase the child count for current vertex
                parent.put(v, u);
                childCount++;
                //make recursive call for the adjacent vertex
                dfs(graph, v, discoveryTime, lowTime, visited, parent, articulationPoints);

                //check for articulation point
                if (discoveryTime.get(u) <= lowTime.get(v)) {
                    canArticulationPoint = true;
                } else {
                    //check the low point is adjacent vertex is less than current vertex,
                    //if yes then update the current vertex low point
                    int currLowTime = lowTime.get(u);
                    lowTime.put(u, Math.min(currLowTime, lowTime.get(v)));
                }
            } else {
                //if here means the vertex was visited earlier,
                //certainly there is a back edge,
                // update the low time of current vertex with visited time of adjacent vertex if needed
                int currLowTime = lowTime.get(u);
                lowTime.put(u, Math.min(currLowTime, discoveryTime.get(v)));
            }
        }
        //after visiting all the adjacent vertices check if current vertex is articulation point
        if ((parent.get(u) == null && childCount > 1) || (parent.get(u) != null && canArticulationPoint)) {
            //current vertex is AP, add it to the list
            articulationPoints.add(u);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(7, false);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(5, 6);

        System.out.println(new FindAllArticulationPoints().findArticulationPoints(graph));
    }
}
