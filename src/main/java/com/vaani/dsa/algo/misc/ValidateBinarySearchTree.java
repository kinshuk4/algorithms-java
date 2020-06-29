package com.vaani.dsa.algo.misc;

import com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode;

/**
 * Definition for binary tree
 * public class BinaryTreeNode {
 * int val;
 * BinaryTreeNode left;
 * BinaryTreeNode right;
 * BinaryTreeNode(int x) { val = x; }
 * }
 */
public class ValidateBinarySearchTree {
    BinaryTreeNode<Integer> previous;
    boolean valid;

    public boolean isValidBST(BinaryTreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        previous = null;
        valid = true;
        isValid(root);
        return valid;
    }

    public void isValid(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        isValid(root.left);
        if (previous != null && previous.value >= root.value) {
            valid = false;
            return;
        }
        previous = root;
        isValid(root.right);
    }

}
