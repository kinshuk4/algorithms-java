package com.vaani.dsa.algo.ds.tree.binary.traversal;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(root);
        ArrayList<TreeNode> parent = list;
        while (!parent.isEmpty()) {
            ArrayList<TreeNode> cur = new ArrayList<>();
            ArrayList<Integer> parentData = new ArrayList<>();
            for (TreeNode n : parent) {
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

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        List<Integer> level = new ArrayList<>();
        queue1.add(root);

        while (!queue1.isEmpty()) {
            TreeNode node = queue1.poll();
            level.add(node.val);

            if (node.left != null) {
                queue2.add(node.left);
            }
            if (node.right != null) {
                queue2.add(node.right);
            }

            if (queue1.isEmpty()) {
                result.add(level);
                level = new ArrayList<>();
                queue1.addAll(queue2);
                queue2.clear();
            }
        }
        Collections.reverse(result);
        return result;
    }

    public List<List<Integer>> levelOrderBottom3(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int numNodesAtCurrLevel = 1;
        int nextLevel = 0;

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < numNodesAtCurrLevel; i++) {
                TreeNode node = queue.poll();
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