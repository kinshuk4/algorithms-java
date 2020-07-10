package com.vaani.dsa.ds.core.tree.binarytree.simple;

public class BinaryTreeNode {
    public int val;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int val) {
        this.val = val;
    }

    public BinaryTreeNode(int val, BinaryTreeNode left, BinaryTreeNode right) {
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
