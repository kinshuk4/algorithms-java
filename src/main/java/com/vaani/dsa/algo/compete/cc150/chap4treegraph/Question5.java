package com.vaani.dsa.algo.compete.cc150.chap4treegraph;

import com.vaani.dsa.ds.core.tree.BinaryTreeNode;

/**
 * Implement a function to check if a binary tree is a binary search tree.
 */
// O(1) space, O(n) time
public class Question5 {

    public boolean isValidBST(BinaryTreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        return validate(root, min, max);
    }

    private boolean validate(BinaryTreeNode<Integer> node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (min < node.value && node.value < max) {
            return validate(node.left, min, node.value)
                    && validate(node.right, node.value, max);
        } else {
            return false;
        }
    }

    // this question is available at leetcode, Validating Binary Search Tree


}
