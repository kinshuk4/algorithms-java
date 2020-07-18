package com.vaani.dsa.algo.ds.tree.binary.traversal;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.*;

/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7]
  [9,20],
  [3],
]
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
*/

public class LevelOrderTraversalBottomUp {
    public List<List<Integer>> levelOrderBottom(BinaryTreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayList<BinaryTreeNode> list = new ArrayList<BinaryTreeNode>();
        list.add(root);
        ArrayList<BinaryTreeNode> parent = list;
        while (!parent.isEmpty()) {
            ArrayList<BinaryTreeNode> cur = new ArrayList<BinaryTreeNode>();
            ArrayList<Integer> parentData = new ArrayList<Integer>();
            for (BinaryTreeNode n : parent) {
                parentData.add(n.val);
                if (n.left != null) {
                    cur.add(n.left);
                }
                if (n.right != null) {
                    cur.add(n.right);
                }
            }
            result.add(0, parentData);
            parent = cur;

        }
        return result;

    }

    public List<List<Integer>> levelOrderBottom2(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Queue<BinaryTreeNode> queue1 = new LinkedList<BinaryTreeNode>();
        Queue<BinaryTreeNode> queue2 = new LinkedList<BinaryTreeNode>();
        List<Integer> level = new ArrayList<Integer>();
        queue1.add(root);

        while (!queue1.isEmpty()) {
            BinaryTreeNode node = queue1.poll();
            level.add(node.val);

            if (node.left != null) {
                queue2.add(node.left);
            }
            if (node.right != null) {
                queue2.add(node.right);
            }

            if (queue1.isEmpty()) {
                result.add(level);
                level = new ArrayList<Integer>();
                queue1.addAll(queue2);
                queue2.clear();
            }
        }
        Collections.reverse(result);
        return result;
    }

    public List<List<Integer>> levelOrderBottom3(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(root);
        int numNodesAtCurrLevel = 1;
        int nextLevel = 0;

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < numNodesAtCurrLevel; i++) {
                BinaryTreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                    nextLevel++;
                }

                if (node.right != null) {
                    queue.add(node.right);
                    nextLevel++;
                }
            }
            result.add(0, level);
            numNodesAtCurrLevel = nextLevel;
            nextLevel = 0;
        }
        return result;
    }
}