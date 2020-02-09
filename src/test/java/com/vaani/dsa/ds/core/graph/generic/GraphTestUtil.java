package com.vaani.dsa.ds.core.graph.generic;

public class GraphTestUtil {
    public static Graph<String> getSimpleUndirectedGraph() {
        Graph<String> stringGraph = new Graph<String>(false);

        //to make the graph un directed use the same weight

        // 1 -- 2 -- 3
        //      |
        //      4 -- 5


        stringGraph.addEdge("1", "2");  //connect v1 v2
        stringGraph.addEdge("2", "3");  //connect v2 v3
        stringGraph.addEdge("2", "4");  //connect v2 v4
        stringGraph.addEdge("4", "5"); //connect v4 v5


        return stringGraph;
    }

    public static Graph<String> getSimpleDirectedGraph() {
        Graph<String> stringGraph = new Graph<String>(true);

        //to make the graph un directed use the same weight

        // 1 --> 2 --> 3
        //       ↓
        //       4 --> 5


        stringGraph.addEdge("1", "2");  //connect v1 v2
        stringGraph.addEdge("2", "3");  //connect v2 v3
        stringGraph.addEdge("2", "4");  //connect v2 v4
        stringGraph.addEdge("4", "5"); //connect v4 v5


        return stringGraph;
    }

    public static Graph<String> getBigUndirectedGraph() {
        // 3 --  4
        // |    |  \
        // 1    5 -- 7 -- 9
        //  \  /    /    /
        //    2 -- 6 -- 8

        Graph<String> stringGraph = new Graph<String>(false);
        stringGraph.addEdge("1", "3");
        stringGraph.addEdge("1", "2");
        stringGraph.addEdge("3", "4");
        stringGraph.addEdge("2", "5");
        stringGraph.addEdge("2", "6");
        stringGraph.addEdge("4", "5");
        stringGraph.addEdge("4", "7");
        stringGraph.addEdge("5", "7");
        stringGraph.addEdge("6", "7");
        stringGraph.addEdge("7", "9");
        stringGraph.addEdge("6", "8");
        stringGraph.addEdge("8", "9");
        return stringGraph;
    }


    public static Graph<String> getBigDirectedGraph() {
        // 3 --> 4
        // 🠥     ↓   ⭨
        // 1    5 --> 7 --> 9
        //  ⭨  ⭧    ⭧     ⭧
        //    2 --> 6 --> 8

        Graph<String> stringGraph = new Graph<String>(true);
        stringGraph.addEdge("1", "3");
        stringGraph.addEdge("1", "2");
        stringGraph.addEdge("3", "4");
        stringGraph.addEdge("2", "5");
        stringGraph.addEdge("2", "6");
        stringGraph.addEdge("4", "5");
        stringGraph.addEdge("4", "7");
        stringGraph.addEdge("5", "7");
        stringGraph.addEdge("6", "7");
        stringGraph.addEdge("7", "9");
        stringGraph.addEdge("6", "8");
        stringGraph.addEdge("8", "9");
        return stringGraph;
    }

    public static Graph<String> getAnotherDirectedGraph() {
        //    5  --> 2
        //  ⭧  ⭨     ⭨
        // 7     4     1 --> 0
        //  ⭨  ⭧    ⭧
        //    6  --> 3

        Graph<String> stringGraph = new Graph<String>(true);
        stringGraph.addEdge("7", "5");
        stringGraph.addEdge("7", "6");
        stringGraph.addEdge("5", "4");
        stringGraph.addEdge("6", "4");
        stringGraph.addEdge("5", "2");
        stringGraph.addEdge("6", "3");
        stringGraph.addEdge("2", "1");
        stringGraph.addEdge("3", "1");
        stringGraph.addEdge("1", "0");

        return stringGraph;
    }

    public static Graph<String> getBipartiteGraph() {
        // a --  e
        //    \
        // b --  f
        //    X
        // c --  g
        //
        // d --  h


        Graph<String> stringGraph = new Graph<String>(false);
        stringGraph.addEdge("a", "e");
        stringGraph.addEdge("a", "f");
        stringGraph.addEdge("b", "g");
        stringGraph.addEdge("c", "f");
        stringGraph.addEdge("d", "h");

        return stringGraph;
    }


}
