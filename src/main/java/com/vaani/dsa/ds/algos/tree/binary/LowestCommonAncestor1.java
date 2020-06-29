package com.vaani.dsa.ds.algos.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode;

/**
 * Given a binary search tree (BST), find the lowest common ancestor of two given nodes in the BST.
 * <p>
 *  on 10/26/2014.
 */
public class LowestCommonAncestor1 {
    /**
     * O(logN) Time
     * Reference: http://leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-search-tree.html
     */
    public static BinaryTreeNode getLCA(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> p, BinaryTreeNode<Integer> q) {
        if (root == null || p == null || q == null) return null;
        if (root.value > Math.max(p.value, q.value)) {
            return getLCA(root.left, p, q);
        } else if (root.value < Math.min(p.value, q.value)) {
            return getLCA(root.right, p, q);
        } else {
            return root;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(6);
        root.left = new BinaryTreeNode(2);
        root.right = new BinaryTreeNode(8);
        root.left.left = new BinaryTreeNode(0);
        root.left.right = new BinaryTreeNode(4);
        root.right.left = new BinaryTreeNode(7);
        root.right.right = new BinaryTreeNode(9);
        root.left.right.left = new BinaryTreeNode(3);
        root.left.right.right = new BinaryTreeNode(5);

        BinaryTreeNode p = new BinaryTreeNode(2);
        BinaryTreeNode q = new BinaryTreeNode(8);
        System.out.println(getLCA(root, p, q).value);
    }
}
