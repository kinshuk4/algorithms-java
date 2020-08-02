package com.vaani.dsa.algo.ds.graph;

import com.vaani.dsa.ds.core.graph.generic.intgraph.UndirectedGraphNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/clone-graph/
 * 133. Clone Graph
 * Medium
 * Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * <p>
 * <p>
 * Test case format:
 * <p>
 * For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.
 * <p>
 * Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 * <p>
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * <p>
 * Example 2:
 * <p>
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * <p>
 * Example 3:
 * <p>
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 * <p>
 * Example 4:
 * <p>
 * Input: adjList = [[2],[1]]
 * Output: [[2],[1]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * Number of Nodes will not exceed 100.
 * There is no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 * <p>
 * <p>
 * Unrelated point in old description of problem:
 */

/*
We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.
The graph has a total of three nodes, and therefore contains three parts as separated by #.
First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.

Visually, the graph looks like the following:
       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 */


/**
 * Definition for undirected graph.
 */
//class UndirectedGraphNode {
//    int label;
//    ArrayList<UndirectedGraphNode> neighbors;
//
//    UndirectedGraphNode(int x) {
//        label = x;
//        neighbors = new ArrayList<UndirectedGraphNode>();
//    }
//};
// NOTE: we dont have to use visited flag here.
public class CloneGraph {

    static class UsingBFS1 {
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
            if (node == null) {
                return null;
            }

            Map<UndirectedGraphNode, UndirectedGraphNode> origToCopyMap = new HashMap<>();

            UndirectedGraphNode head = new UndirectedGraphNode(node.val);
            Queue<UndirectedGraphNode> queue = new LinkedList<>();
            queue.add(node);
            origToCopyMap.put(node, head);

            while (!queue.isEmpty()) {
                UndirectedGraphNode curr = queue.poll();
                for (UndirectedGraphNode adjNode : curr.neighbors) {

                    if (!origToCopyMap.containsKey(adjNode)) {
                        UndirectedGraphNode adjNodeCopy = new UndirectedGraphNode(adjNode.val);
                        queue.add(adjNode);
                        origToCopyMap.put(adjNode, adjNodeCopy);
                        origToCopyMap.get(curr).neighbors.add(adjNodeCopy);
                    } else {
                        origToCopyMap.get(curr).neighbors.add(origToCopyMap.get(adjNode));
                    }

                }
            }
            return head;
        }
    }

    static class UsingBFS2 {
        public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
            if (node == null) {
                return null;
            }
            Queue<UndirectedGraphNode> queue = new LinkedList<>();
            Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                UndirectedGraphNode oldNode = queue.poll();
                UndirectedGraphNode newNode;
                if (!map.containsKey(oldNode)) {
                    newNode = new UndirectedGraphNode(oldNode.val);
                    map.put(oldNode, newNode);
                } else {
                    newNode = map.get(oldNode);
                }
                for (UndirectedGraphNode oldNeighbor : oldNode.neighbors) {
                    UndirectedGraphNode newNeighbor;
                    if (!map.containsKey(oldNeighbor)) {
                        newNeighbor = new UndirectedGraphNode(oldNeighbor.val);
                        queue.add(oldNeighbor);
                        map.put(oldNeighbor, newNeighbor);
                    } else {
                        newNeighbor = map.get(oldNeighbor);
                    }
                    newNode.neighbors.add(newNeighbor);
                }
            }
            return map.get(node);
        }
    }


}