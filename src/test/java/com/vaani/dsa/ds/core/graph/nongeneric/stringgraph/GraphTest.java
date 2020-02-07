package com.vaani.dsa.ds.core.graph.nongeneric.stringgraph;

import com.vaani.dsa.ds.core.graph.nongeneric.stringgraph.Graph;
import com.vaani.dsa.ds.core.graph.nongeneric.stringgraph.Vertex;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.out;
class GraphTest {

    private Graph getSimpleGraph() {
        Graph graph = new Graph(false);

        //to make the graph un directed use the same weight

        // 1 -- 2 -- 3
        //      |
        //      4 -- 5


        graph.addEdge("1", "2");  //connect v1 v2
        graph.addEdge("2", "3");  //connect v2 v3
        graph.addEdge("2", "4");  //connect v2 v4
        graph.addEdge("4", "5"); //connect v4 v5


        return graph;
    }

    private Graph getBigGraph() {
        // 3 --  4
        // |    |  \
        // 1    5 -- 7 -- 9
        //  \  /    /    /
        //    2 -- 6 -- 8

        Graph graph = new Graph(false);
        graph.addEdge("1", "3");
        graph.addEdge("1", "2");
        graph.addEdge("3", "4");
        graph.addEdge("2", "5");
        graph.addEdge("2", "6");
        graph.addEdge("4", "5");
        graph.addEdge("4", "7");
        graph.addEdge("5", "7");
        graph.addEdge("6", "7");
        graph.addEdge("7", "9");
        graph.addEdge("6", "8");
        graph.addEdge("8", "9");
        return graph;
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
        Graph g = getSimpleGraph();
        Set<Vertex> bfsResult = g.breadthFirstTraversal("1");

        // using list to verify ordering of traversal - https://stackoverflow.com/questions/45169421/junit-test-for-order-in-linkedhashset
        List<String> expectedResult = Arrays.asList("1", "2", "3", "4", "5");

        bfsResult.forEach(out::println);

        Assert.assertEquals(expectedResult, bfsResult.stream().map(Vertex::getLabel).collect(Collectors.toList()));

        Graph bigG = getBigGraph();
        Set<Vertex> bfs2 = bigG.breadthFirstTraversal("1");
        List <String> expectedResult2 = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Assert.assertEquals(expectedResult2, bfs2.stream().map(Vertex::getLabel).collect(Collectors.toList()));
    }

    @Test
    void depthFirstTraversal() {
        Graph g = getSimpleGraph();
        Set<Vertex> dfsResult = g.depthFirstTraversal("1");
        List<String> expectedResult = Arrays.asList("1", "2", "4", "5", "3");
        List<String> anotherExpectedResult = Arrays.asList("1", "2", "3", "4", "5");
        dfsResult.forEach(out::println);

        List<String> actualResult = dfsResult.stream().map(Vertex::getLabel).collect(Collectors.toList());

        Assert.assertTrue(expectedResult.equals(actualResult) || anotherExpectedResult.equals(actualResult)) ;

        Graph bigG = getBigGraph();
        Set<Vertex> dfsResult2 = bigG.depthFirstTraversal("1");
        List <String> expectedResult2 = Arrays.asList("1", "3", "4", "5", "7", "9", "2", "6", "8");
        Assert.assertEquals(expectedResult2, dfsResult2.stream().map(Vertex::getLabel).collect(Collectors.toList()));
    }

    @Test
    void dfsRecursion() {
        Graph g = getSimpleGraph();
        Set<Vertex> dfsResult = g.dfsRecursion("1");
        List<String> expectedResult = Arrays.asList("1", "2", "4", "5", "3");

        dfsResult.forEach(out::println);

        Assert.assertEquals(expectedResult, dfsResult.stream().map(Vertex::getLabel).collect(Collectors.toList()));

        Graph bigG = getBigGraph();
        Set<Vertex> dfsResult2 = bigG.dfsRecursion("1");
        List <String> expectedResult2 = Arrays.asList("1", "3", "4", "5", "7", "9", "2", "6", "8");
        Assert.assertEquals(expectedResult2, dfsResult2.stream().map(Vertex::getLabel).collect(Collectors.toList()));
    }

    @Test
    void topologicalSorting() {
        Graph g = getSimpleGraph();
        Set<Vertex> dfsResult = g.dfsRecursion("1");
        List<String> expectedResult = Arrays.asList("1", "2", "4", "5", "3");

        dfsResult.forEach(out::println);

        Assert.assertEquals(expectedResult, dfsResult.stream().map(Vertex::getLabel).collect(Collectors.toList()));

        Graph bigG = getBigGraph();
        Set<Vertex> dfsResult2 = bigG.dfsRecursion("1");
        List <String> expectedResult2 = Arrays.asList("1", "3", "4", "5", "7", "9", "2", "6", "8");
        Assert.assertEquals(expectedResult2, dfsResult2.stream().map(Vertex::getLabel).collect(Collectors.toList()));
    }

}