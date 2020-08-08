package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * 111. Minimum Depth of Binary Tree
 * Easy
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * <p>
 */
public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
//        root.left = new BinaryTreeNode(2);

        MinimumDepthOfBinaryTree test = new MinimumDepthOfBinaryTree();
        System.out.println(test.minDepth(root));
    }

    /**
     * DFS
     */
    public int minDepth(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * DFS
     */
    public int minDepthWithHelper(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        return getDepth(root);
    }

    @SuppressWarnings("Duplicates")
    private int getDepth(BinaryTreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        } else if (root.left == null) {
            return getDepth(root.right) + 1;
        } else if (root.right == null) {
            return getDepth(root.left) + 1;
        } else {
            return Math.min(getDepth(root.left), getDepth(root.right)) + 1;
        }
    }

    /**
     * Level-first traversal
     */
    public int minDepthIterative(BinaryTreeNode root) {
        if (root == null) return 0;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                BinaryTreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return level;
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            level++;
        }
        return 0;
    }
}
