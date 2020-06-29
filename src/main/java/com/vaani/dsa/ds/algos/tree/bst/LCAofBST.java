package com.vaani.dsa.ds.algos.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode;

/**
 * Find the LCA of the binary search tree.
 */
public class LCAofBST {
    /**
     * O(logn) time, O(1) space
     */
    public class Solution {
        BinaryTreeNode LCA(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> q) {
            if (root == null) {
                return null;
            }

            if (root.value < p.value && root.value < q.value) { // smaller than both
                return LCA(root.right, p, q);
            } else if (root.value > p.value && root.value > q.value) { // bigger than both
                return LCA(root.left, p, q);
            } else {
                return root;
            }

        }
    }
}
