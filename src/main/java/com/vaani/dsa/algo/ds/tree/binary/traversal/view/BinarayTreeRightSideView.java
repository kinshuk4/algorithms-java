package com.vaani.dsa.algo.ds.tree.binary.traversal.view;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * Given a binary tree, imagine yourself standing on
 * the right side of it, return the values of the nodes you can see ordered from top to bottom.
 */

/*
Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 */
public class BinarayTreeRightSideView {

    private int maxHeigh = Integer.MIN_VALUE;
    List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BinaryTreeNode root = new BinaryTreeNode(2);
        root.left = new BinaryTreeNode(3);
        root.right = new BinaryTreeNode(4);
        root.right.right = new BinaryTreeNode(5);
        root.right.left = new BinaryTreeNode(4);
        root.right.left.right = new BinaryTreeNode(8);
        root.right.left.left = new BinaryTreeNode(7);
        root.right.left.left.right = new BinaryTreeNode(10);
        root.right.left.left.left = new BinaryTreeNode(7);

        List<Integer> list = new BinarayTreeRightSideView().rightSideViewRecursive1(root);
    }

    public List<Integer> rightSideViewRecursive1(BinaryTreeNode root) {
        if (root == null) return list;
        dfs(root, 0);
        return list;
    }

    private void dfs(BinaryTreeNode node, int height) {
        if (node != null) {
            if (height > maxHeigh) {
                list.add(node.val);
                maxHeigh = height;
            }
            dfs(node.right, height + 1);
            dfs(node.left, height + 1);
        }
    }

    public List<Integer> rightSideViewIterative(BinaryTreeNode root) {
        List<Integer> result = new LinkedList<>();

        if (root == null) {
            return result;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                BinaryTreeNode curr = queue.poll();
                if (i == queueSize - 1) {
                    result.add(curr.val);
                }

                if (curr.left != null) {
                    queue.offer(curr.left);
                }


                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }

        return result;
    }


}
