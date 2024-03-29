package com.vaani.dsa.algo.ds.tree.binary.traversal;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;
import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
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

public class LevelOrderTraversalByLevel {
    public static void main(String[] args) {
//        BinaryTreeNode root = SerializeBinaryTree.deserializeWithLevelOrder("3,9,20,#,#,15,7");
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> expected = new ArrayList<>() {{
            add(new ArrayList<>(Stream.of(3).collect(Collectors.toList())));
            add(new ArrayList<>(Stream.of(9, 20).collect(Collectors.toList())));
            add(new ArrayList<>(Stream.of(15, 7).collect(Collectors.toList())));
        }};

        LevelOrderTraversalByLevel underTest = new LevelOrderTraversalByLevel();
        Assert.assertEquals(expected, underTest.levelOrder(root));
    }

    public static List<List<Integer>> levelOrder1(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<TreeNode> currentList = new ArrayList<>();
        currentList.add(root);


        while (currentList.size() != 0) {
            List<TreeNode> nextLevelList = new ArrayList<>();
            List<Integer> parentLevelData = new ArrayList<>();

            for (TreeNode s : currentList) {
                parentLevelData.add(s.val);
                if (s.left != null) {
                    nextLevelList.add(s.left);
                }
                if (s.right != null) {
                    nextLevelList.add(s.right);
                }
            }
            result.add(parentLevelData);
            //currentList = new ArrayList<BinaryTreeNode>(nextLevelList);
            currentList = nextLevelList;
        }

        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {

                TreeNode curr = queue.poll();
                currentLevel.add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }


            }

            result.add(currentLevel);
        }

        return result;
    }
}