package com.vaani.dsa.algo.compete.codesignal.treeadvanced;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.*;

import static com.vaani.dsa.ds.algos.tree.binary.TreeDiameter.getBinaryTreeDiameter;

/**
 * f course a tree height can be calculated as the length of the longest path in it (it is also called tree diameter). So, now you have a task you need to solve, so go ahead!
 * <p>
 * Example
 * <p>
 * For n = 10 and
 * <p>
 * tree = [[2, 5], [5, 7], [5, 1], [1, 9], [1, 0], [7, 6], [6, 3], [3, 8], [8, 4]]
 * the output should be treeDiameter(n, tree) = 7.
 * <p>
 * <p>
 * <p>
 * The longest path is the path from vertex 4 to one vertex 9 or 0.
 * <p>
 * Input/Output
 * <p>
 * [execution time limit] 3 seconds (java)
 * <p>
 * [input] integer n
 * <p>
 * The number of vertices in the structure you drew in your notebook.
 * <p>
 * Guaranteed constraints:
 * 1 ≤ n ≤ 104.
 * <p>
 * [input] array.array.integer tree
 * <p>
 * Edges of the tree. tree[i] for each valid i contains two elements and represents an edge that connects tree[i][0] and tree[i][1].
 * It is guaranteed that given graph is a tree, i.e. it is connected and has no cycles.
 * <p>
 * Guaranteed constraints:
 * tree.length = n - 1,
 * tree[i].length = 2,
 * 0 ≤ tree[i][j] < n.
 */
public class TreeDiameter {
    static int treeDiameter(int n, int[][] tree) {
        Map<Integer, BinaryTreeNode> map = new HashMap<>();
        BinaryTreeNode root = null;
        for (int[] edge : tree) {
            BinaryTreeNode node = new BinaryTreeNode(edge[1]);
            map.put(edge[1], node);
            if (map.containsKey(edge[0])) {
                BinaryTreeNode parent = map.get(edge[0]);
                if (parent.left != null) {
                    parent.right = node;
                } else {
                    parent.left = node;
                }
            } else {
                BinaryTreeNode parent = new BinaryTreeNode(edge[0]);
                if (root == null) {
                    root = parent;
                }
                map.put(edge[0], node);
                parent.left = node;
            }

        }

        return getBinaryTreeDiameter(root);


    }

    public static void main(String[] args) {
        int[][] edges = {
                {2, 5},
                {5, 7},
                {5, 1},
                {1, 9},
                {1, 0},
                {7, 6},
                {6, 3},
                {3, 8},
                {8, 4}
        };
        System.out.println(treeDiameter(10, edges));
    }

}
