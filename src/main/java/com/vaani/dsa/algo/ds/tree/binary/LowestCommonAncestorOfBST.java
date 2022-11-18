package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

/**https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * Given a binary search tree (BST), find the lowest common ancestor of two given nodes in the BST.
 * <p>
 *
 */
public class LowestCommonAncestorOfBST {

    public static TreeNode getLCA1(TreeNode root, TreeNode p, TreeNode q) {
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
    public static TreeNode getLCA2(TreeNode root, TreeNode p, TreeNode q) {
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
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);
        System.out.println(getLCA2(root, p, q).val);
    }
}
