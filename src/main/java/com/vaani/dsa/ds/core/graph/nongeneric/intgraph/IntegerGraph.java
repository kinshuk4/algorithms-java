package com.vaani.dsa.ds.core.graph.nongeneric.intgraph;

import com.vaani.dsa.ds.core.graph.generic.Graph;
import com.vaani.dsa.ds.core.graph.generic.Vertex;

import java.util.*;


public class IntegerGraph extends Graph<Integer> {
    public IntegerGraph(int numberVertices) {
        super();
        for(int i=0;i<numberVertices;i++){
            getAdjList().put(new Vertex<Integer>(i), new HashSet<>());
        }
    }
}
