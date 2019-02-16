package com.vaani.algo.ds.algos.graph;

import com.vaani.algo.ds.core.graph.UndirectedGraphNode;

import java.util.*;

//formatter:off
/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
OJ's undirected graph serialization:
Nodes are labeled uniquely.
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

//formatter:on

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
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (node == null) {
            return null;
        }
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.add(node);
        map.put(node, head);

        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            for (UndirectedGraphNode n : cur.neighbors) {

                if (!map.containsKey(n)) {
                    UndirectedGraphNode nb = new UndirectedGraphNode(n.label);
                    queue.add(n);
                    map.put(n, nb);
                    map.get(cur).neighbors.add(nb);
                } else {
                    map.get(cur).neighbors.add(map.get(n));
                }

            }
        }
        return head;

    }

    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        if (node == null) return null;
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        queue.add(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode oldNode = queue.poll();
            UndirectedGraphNode newNode;
            if (!map.containsKey(oldNode)) {
                newNode = new UndirectedGraphNode(oldNode.label);
                map.put(oldNode, newNode);
            } else {
                newNode = map.get(oldNode);
            }
            for (UndirectedGraphNode oldNeighbor : oldNode.neighbors) {
                UndirectedGraphNode newNeighbor;
                if (!map.containsKey(oldNeighbor)) {
                    newNeighbor = new UndirectedGraphNode(oldNeighbor.label);
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