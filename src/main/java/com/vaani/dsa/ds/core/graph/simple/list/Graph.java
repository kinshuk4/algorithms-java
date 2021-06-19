package com.vaani.dsa.ds.core.graph.simple.list;

import java.util.LinkedList;

public class Graph{
    public int vertices;
    public LinkedList<Integer> adjList [];
    private boolean isDirected;

    public Graph(int vertices, boolean isDirected){
        this.vertices = vertices;
        this.isDirected = isDirected;
        adjList = new LinkedList[vertices];
        for (int i = 0; i <vertices ; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination){
        //forward edge
        adjList[source].add(destination);
        //backward edge in undirected graph
        if(!isDirected) {
            adjList[destination].add(source);
        }
    }
}
