package com.vaani.dsa.ds.algos.tree.binary;

import com.vaani.dsa.ds.core.tree.BinaryTreeNode;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * Created by Xiaomeng on 8/9/2014.
 */
public class ValidateBST {
    public boolean isValidBST(BinaryTreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValidBST(BinaryTreeNode<Integer> root, int min, int max) {
        if (root == null) return true;
        if (root.value > min && root.value < max
                && isValidBST(root.left, min, root.value)
                && isValidBST(root.right, root.value, max)) {
            return true;
        } else {
            return false;
        }
    }
}
