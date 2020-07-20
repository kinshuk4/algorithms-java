package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/** https://leetcode.com/problems/univalued-binary-tree/
 * A binary tree is univalued if every node in the tree has the same value.
 * <p>
 * Return true if and only if the given tree is univalued.
 */
public class IsUniValuedBinaryTree {

    static boolean isUnivalTree(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }

        boolean left = isUnivalTree(root.left);
        boolean right = isUnivalTree(root.right);

        // If any of the subtrees is not singly, then this
        // cannot be singly.
        if (!left || !right) {
            return false;
        }

        // If left subtree is singly and non-empty, but data
        // doesn't match
        if (root.left != null && !(root.val == root.left.val)) {
            return false;
        }

        // Same for right subtree
        if (root.right != null && !(root.val == root.right.val)) {
            return false;
        }

        return true;
    }
}
