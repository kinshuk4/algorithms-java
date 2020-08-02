package com.vaani.dsa.ds.core.graph.generic.intgraph;

import java.util.ArrayList;
import java.util.List;


public class UndirectedGraphNode {
    public int val;
    public List<UndirectedGraphNode> neighbors;
    public boolean visited;

    public UndirectedGraphNode(int x) {
        val = x;
        neighbors = new ArrayList<>();
        visited = false;
    }
}
