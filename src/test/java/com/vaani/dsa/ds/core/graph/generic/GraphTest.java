package com.vaani.dsa.ds.core.graph.generic;


import org.junit.jupiter.api.Test;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.out;

class GraphTest {

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
        Graph<String> g = GraphTestUtil.getSimpleUndirectedGraph();
        out.println(g);
        Set<Vertex<String>> bfsResult = g.breadthFirstTraversal("1");

        // using list to verify ordering of traversal - https://stackoverflow.com/questions/45169421/junit-test-for-order-in-linkedhashset
        List<String> expectedResult = Arrays.asList("1", "2", "3", "4", "5");

        bfsResult.forEach(out::println);

        Assert.assertEquals(expectedResult, bfsResult.stream().map(Vertex::getValue).collect(Collectors.toList()));

        Graph<String> bigG = GraphTestUtil.getBigUndirectedGraph();
        Set<Vertex<String>> bfs2 = bigG.breadthFirstTraversal("1");
        List<String> actualResult2 = bfs2.stream().map(Vertex::getValue).collect(Collectors.toList());

        List<String> expectedResult2 = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        List<String> anotherExpectedResult2 = Arrays.asList("1", "2", "3", "5", "6", "4", "7", "8", "9");


        Assert.assertTrue(expectedResult2.equals(actualResult2) || anotherExpectedResult2.equals(actualResult2));
    }

    @Test
    void depthFirstTraversal() {
        Graph<String> g = GraphTestUtil.getSimpleUndirectedGraph();
        Set<Vertex<String>> dfsResult = g.depthFirstTraversal("1");
        List<String> expectedResult = Arrays.asList("1", "2", "4", "5", "3");
        List<String> anotherExpectedResult = Arrays.asList("1", "2", "3", "4", "5");
        dfsResult.forEach(out::println);

        List<String> actualResult = dfsResult.stream().map(Vertex<String>::getValue).collect(Collectors.toList());

        Assert.assertTrue(expectedResult.equals(actualResult) || anotherExpectedResult.equals(actualResult));

        Graph<String> bigG = GraphTestUtil.getBigUndirectedGraph();
        Set<Vertex<String>> dfsResult2 = bigG.depthFirstTraversal("1");
        List<String> expectedResult2 = Arrays.asList("1", "3", "4", "5", "7", "9", "8", "6", "2");
        Assert.assertEquals(expectedResult2, dfsResult2.stream().map(Vertex::getValue).collect(Collectors.toList()));
    }

    @Test
    void dfsRecursion() {
        Graph<String> g = GraphTestUtil.getSimpleUndirectedGraph();
        Set<Vertex<String>> dfsResult = g.dfsRecursion("1");
        List<String> expectedResult = Arrays.asList("1", "2", "3", "4", "5");

        dfsResult.forEach(out::println);

        Assert.assertEquals(expectedResult, dfsResult.stream().map(Vertex::getValue).collect(Collectors.toList()));

        Graph<String> bigG = GraphTestUtil.getBigUndirectedGraph();
        Set<Vertex<String>> dfsResult2 = bigG.dfsRecursion("1");
        List<String> expectedResult2 = Arrays.asList("1", "3", "4", "5", "7", "9", "2", "6", "8");
        List<String> anotherExpectedResult2 = Arrays.asList("1", "2", "5", "4", "7", "9", "2", "6", "8");
        List<String> anotherExpectedResult3 = Arrays.asList("1", "2", "5", "4", "7", "6", "8", "9", "3");
        List<String> actualResult2 = dfsResult2.stream().map(Vertex::getValue).collect(Collectors.toList());
        Assert.assertEquals(anotherExpectedResult3, actualResult2);
        Assert.assertTrue(expectedResult2.equals(actualResult2) || anotherExpectedResult3.equals(actualResult2));
    }

    @Test
    void topologicalSorting() {
        Graph<String> g = GraphTestUtil.getSimpleDirectedGraph();
        List<Vertex<String>> dfsResult = g.topologicalSorting();
        List<String> expectedResult = Arrays.asList("1", "2", "4", "5", "3");

        Assert.assertEquals(expectedResult, dfsResult.stream().map(Vertex::getValue).collect(Collectors.toList()));

        Graph<String> bigG = GraphTestUtil.getBigDirectedGraph();
        List<Vertex<String>> dfsResult2 = bigG.topologicalSorting();
        List<String> expectedResult2 = Arrays.asList("1", "3", "4", "2", "6", "8", "5", "7", "9");
        Assert.assertEquals(expectedResult2, dfsResult2.stream().map(Vertex::getValue).collect(Collectors.toList()));
    }

    @Test
    void hasCycle() {
        Graph<String> g = GraphTestUtil.getSimpleUndirectedGraph();
        out.println(g.hasCycle());
        Assert.assertFalse(g.hasCycle());

        g = GraphTestUtil.getBigUndirectedGraph();
        Assert.assertTrue(g.hasCycle());

        g = GraphTestUtil.getSimpleDirectedGraph();
        Assert.assertFalse(g.hasCycle());

        g = GraphTestUtil.getBigDirectedGraph();
        Assert.assertTrue(g.hasCycle());
    }
}