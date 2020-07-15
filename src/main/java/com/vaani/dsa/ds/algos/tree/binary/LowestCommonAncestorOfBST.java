package com.vaani.dsa.ds.algos.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * Given a binary search tree (BST), find the lowest common ancestor of two given nodes in the BST.
 * <p>
 *
 */
public class LowestCommonAncestorOfBST {

    public static BinaryTreeNode getLCA1(BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        if (root.val > p.val && root.val > q.val) {
            return getLCA1(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return getLCA1(root.right, p, q);
        } else {
            return root;
        }
    }

    /**
     * O(logN) Time
     * Reference:
     */
    public static BinaryTreeNode getLCA2(BinaryTreeNode root, BinaryTreeNode p, BinaryTreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        if (root.val > Math.max(p.val, q.val)) {
            return getLCA2(root.left, p, q);
        } else if (root.val < Math.min(p.val, q.val)) {
            return getLCA2(root.right, p, q);
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
        System.out.println(getLCA2(root, p, q).val);
    }
}
