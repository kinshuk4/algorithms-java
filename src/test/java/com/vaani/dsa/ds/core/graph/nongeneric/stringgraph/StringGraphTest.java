package com.vaani.dsa.ds.core.graph.nongeneric.stringgraph;

import com.vaani.dsa.ds.core.graph.generic.stringgraph.StringGraph;
import com.vaani.dsa.ds.core.graph.generic.stringgraph.StringVertex;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.out;
class StringGraphTest {

    private StringGraph getSimpleGraph() {
        StringGraph stringGraph = new StringGraph(false);

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

    private StringGraph getBigGraph() {
        // 3 --  4
        // |    |  \
        // 1    5 -- 7 -- 9
        //  \  /    /    /
        //    2 -- 6 -- 8

        StringGraph stringGraph = new StringGraph(false);
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
        StringGraph g = getSimpleGraph();
        Set<StringVertex> bfsResult = g.breadthFirstTraversal("1");

        // using list to verify ordering of traversal - https://stackoverflow.com/questions/45169421/junit-test-for-order-in-linkedhashset
        List<String> expectedResult = Arrays.asList("1", "2", "3", "4", "5");

        bfsResult.forEach(out::println);

        Assert.assertEquals(expectedResult, bfsResult.stream().map(StringVertex::getLabel).collect(Collectors.toList()));

        StringGraph bigG = getBigGraph();
        Set<StringVertex> bfs2 = bigG.breadthFirstTraversal("1");
        List <String> expectedResult2 = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9");
        Assert.assertEquals(expectedResult2, bfs2.stream().map(StringVertex::getLabel).collect(Collectors.toList()));
    }

    @Test
    void depthFirstTraversal() {
        StringGraph g = getSimpleGraph();
        Set<StringVertex> dfsResult = g.depthFirstTraversal("1");
        List<String> expectedResult = Arrays.asList("1", "2", "4", "5", "3");
        List<String> anotherExpectedResult = Arrays.asList("1", "2", "3", "4", "5");
        dfsResult.forEach(out::println);

        List<String> actualResult = dfsResult.stream().map(StringVertex::getLabel).collect(Collectors.toList());

        Assert.assertTrue(expectedResult.equals(actualResult) || anotherExpectedResult.equals(actualResult)) ;

        StringGraph bigG = getBigGraph();
        Set<StringVertex> dfsResult2 = bigG.depthFirstTraversal("1");
        List <String> expectedResult2 = Arrays.asList("1", "3", "4", "5", "7", "9", "2", "6", "8");
        Assert.assertEquals(expectedResult2, dfsResult2.stream().map(StringVertex::getLabel).collect(Collectors.toList()));
    }

    @Test
    void dfsRecursion() {
        StringGraph g = getSimpleGraph();
        Set<StringVertex> dfsResult = g.dfsRecursion("1");
        List<String> expectedResult = Arrays.asList("1", "2", "4", "5", "3");

        dfsResult.forEach(out::println);

        Assert.assertEquals(expectedResult, dfsResult.stream().map(StringVertex::getLabel).collect(Collectors.toList()));

        StringGraph bigG = getBigGraph();
        Set<StringVertex> dfsResult2 = bigG.dfsRecursion("1");
        List <String> expectedResult2 = Arrays.asList("1", "3", "4", "5", "7", "9", "2", "6", "8");
        Assert.assertEquals(expectedResult2, dfsResult2.stream().map(StringVertex::getLabel).collect(Collectors.toList()));
    }

    @Test
    void topologicalSorting() {
        StringGraph g = getSimpleGraph();
        Set<StringVertex> dfsResult = g.dfsRecursion("1");
        List<String> expectedResult = Arrays.asList("1", "2", "4", "5", "3");

        dfsResult.forEach(out::println);

        Assert.assertEquals(expectedResult, dfsResult.stream().map(StringVertex::getLabel).collect(Collectors.toList()));

        StringGraph bigG = getBigGraph();
        Set<StringVertex> dfsResult2 = bigG.dfsRecursion("1");
        List <String> expectedResult2 = Arrays.asList("1", "3", "4", "5", "7", "9", "2", "6", "8");
        Assert.assertEquals(expectedResult2, dfsResult2.stream().map(StringVertex::getLabel).collect(Collectors.toList()));
    }

}