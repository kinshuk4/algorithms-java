package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/invert-binary-tree/
 */

/*
Example:

Input:

     4
   /   \
  2     7
 / \   / \
1   3 6   9
Output:

     4
   /   \
  7     2
 / \   / \
9   6 3   1

 */
public class InvertBinaryTree {
    public BinaryTreeNode invertTreeIterative(BinaryTreeNode root) {
        LinkedList<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();

        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            BinaryTreeNode p = queue.poll();
            if (p.left != null)
                queue.add(p.left);
            if (p.right != null)
                queue.add(p.right);

            BinaryTreeNode temp = p.left;
            p.left = p.right;
            p.right = temp;
        }

        return root;
    }

    public BinaryTreeNode invertTreeRecursive1(BinaryTreeNode root) {
        helper(root);
        return root;
    }

    public void helper(BinaryTreeNode n) {
        if (n == null) {
            return;
        }

        BinaryTreeNode t = n.left;
        n.left = n.right;
        n.right = t;

        helper(n.left);
        helper(n.right);
    }

    public BinaryTreeNode invertTreeRecursive2(BinaryTreeNode root) {
        if (root == null) {
            return root;
        }

        invertTreeRecursive2(root.left);
        invertTreeRecursive2(root.right);

        BinaryTreeNode t = root.left;
        root.left = root.right;
        root.right = t;

        return root;
    }


    public BinaryTreeNode invertTreeRecursive3(BinaryTreeNode root) {
        if (root == null) {
            return root;
        }

        invertTreeHelper(root.left, root.right);
        return root;
    }

    private void invertTreeHelper(BinaryTreeNode left, BinaryTreeNode right) {
        BinaryTreeNode temp = left;
        left = right;
        right = temp;
        if (left.left != null) {
            invertTreeHelper(left.left, left.right);
        }

        if (right.left != null) {
            invertTreeHelper(right.left, right.right);
        }
    }
}
