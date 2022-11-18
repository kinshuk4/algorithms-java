package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

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
    public TreeNode invertTreeIterative(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            if (p.left != null)
                queue.add(p.left);
            if (p.right != null)
                queue.add(p.right);

            TreeNode temp = p.left;
            p.left = p.right;
            p.right = temp;
        }

        return root;
    }

    public TreeNode invertTreeRecursive1(TreeNode root) {
        helper(root);
        return root;
    }

    public void helper(TreeNode n) {
        if (n == null) {
            return;
        }

        TreeNode t = n.left;
        n.left = n.right;
        n.right = t;

        helper(n.left);
        helper(n.right);
    }

    public TreeNode invertTreeRecursive2(TreeNode root) {
        if (root == null) {
            return root;
        }

        invertTreeRecursive2(root.left);
        invertTreeRecursive2(root.right);

        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;

        return root;
    }


    public TreeNode invertTreeRecursive3(TreeNode root) {
        if (root == null) {
            return root;
        }

        invertTreeHelper(root.left, root.right);
        return root;
    }

    private void invertTreeHelper(TreeNode left, TreeNode right) {
        TreeNode temp = left;
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
