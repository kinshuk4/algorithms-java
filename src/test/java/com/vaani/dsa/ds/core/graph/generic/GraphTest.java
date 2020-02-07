package com.vaani.dsa.ds.core.graph.generic;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.out;
class GraphTest {

    private Graph<String> getSimpleGraph() {
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

    private Graph<String> getBigGraph() {
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

    @Test
    void getVertices() {
    }

    @Test
    void addVertex() {
    }

    @Test
    void removeVertex() {
    }

    @Test
    void addEdge() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void breadthFirstTraversal() {
        Graph<String> g = getSimpleGraph();
        out.println(g);
        Set<Vertex<String>> bfsResult = g.breadthFirstTraversal("1");

        // using list to verify ordering of traversal - https://stackoverflow.com/questions/45169421/junit-test-for-order-in-linkedhashset
        List<String> expectedResult = Arrays.asList("1", "2", "3", "4", "5");

        bfsResult.forEach(out::println);

        Assert.assertEquals(expectedResult, bfsResult.stream().map(Vertex::getValue).collect(Collectors.toList()));

        Graph<String> bigG = getBigGraph();
        Set<Vertex<String>> bfs2 = bigG.breadthFirstTraversal("1");
        List<String> actualResult2 = bfs2.stream().map(Vertex::getValue).collect(Collectors.toList());

        List <String> expectedResult2 = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        List<String> anotherExpectedResult2 = Arrays.asList("1", "2", "3", "5", "6", "4", "7", "8", "9");


        Assert.assertTrue(expectedResult2.equals(actualResult2) || anotherExpectedResult2.equals(actualResult2)) ;
    }

    @Test
    void depthFirstTraversal() {
        Graph<String> g = getSimpleGraph();
        Set<Vertex<String>> dfsResult = g.depthFirstTraversal("1");
        List<String> expectedResult = Arrays.asList("1", "2", "4", "5", "3");
        List<String> anotherExpectedResult = Arrays.asList("1", "2", "3", "4", "5");
        dfsResult.forEach(out::println);

        List<String> actualResult = dfsResult.stream().map(Vertex<String>::getValue).collect(Collectors.toList());

        Assert.assertTrue(expectedResult.equals(actualResult) || anotherExpectedResult.equals(actualResult)) ;

        Graph<String> bigG = getBigGraph();
        Set<Vertex<String>> dfsResult2 = bigG.depthFirstTraversal("1");
        List <String> expectedResult2 = Arrays.asList("1", "3", "4", "5", "7", "9", "2", "6", "8");
        Assert.assertEquals(expectedResult2, dfsResult2.stream().map(Vertex::getValue).collect(Collectors.toList()));
    }

    @Test
    void dfsRecursion() {
        Graph<String> g = getSimpleGraph();
        Set<Vertex<String>> dfsResult = g.dfsRecursion("1");
        List<String> expectedResult = Arrays.asList("1", "2", "4", "5", "3");

        dfsResult.forEach(out::println);

        Assert.assertEquals(expectedResult, dfsResult.stream().map(Vertex::getValue).collect(Collectors.toList()));

        Graph<String> bigG = getBigGraph();
        Set<Vertex<String>> dfsResult2 = bigG.dfsRecursion("1");
        List <String> expectedResult2 = Arrays.asList("1", "3", "4", "5", "7", "9", "2", "6", "8");
        Assert.assertEquals(expectedResult2, dfsResult2.stream().map(Vertex::getValue).collect(Collectors.toList()));
    }

    @Test
    void topologicalSorting() {
        Graph<String> g = getSimpleGraph();
        Set<Vertex<String>> dfsResult = g.dfsRecursion("1");
        List<String> expectedResult = Arrays.asList("1", "2", "4", "5", "3");

        dfsResult.forEach(out::println);

        Assert.assertEquals(expectedResult, dfsResult.stream().map(Vertex::getValue).collect(Collectors.toList()));

        Graph<String> bigG = getBigGraph();
        Set<Vertex<String>> dfsResult2 = bigG.dfsRecursion("1");
        List <String> expectedResult2 = Arrays.asList("1", "3", "4", "5", "7", "9", "2", "6", "8");
        Assert.assertEquals(expectedResult2, dfsResult2.stream().map(Vertex::getValue).collect(Collectors.toList()));
    }

}