package com.vaani.dsa.ds.core.tree.binarytree.simple;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return this.val +
                ", left=" + (left != null ? left.val + "..." : "null") +
                ", right=" + (right != null ? right.val + "..." : "null");
    }
}
