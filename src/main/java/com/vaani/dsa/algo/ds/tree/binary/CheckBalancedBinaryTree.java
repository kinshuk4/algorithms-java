package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * on 8/11/2014.
 */
public class CheckBalancedBinaryTree {
    public boolean isBalanced(BinaryTreeNode root) {
        return getBalance(root) != -1;
    }

    public int getBalance(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = getBalance(root.left);
        if (left == -1) {
            return -1;
        }

        int right = getBalance(root.right);
        if (right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return left > right ? left + 1 : right + 1;
    }

    public boolean isBalanced2(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }
        int diff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(diff) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int getHeight(BinaryTreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = getHeight(node.left) + 1;
        int right = getHeight(node.right) + 1;
        return Math.max(left, right);
    }
}
