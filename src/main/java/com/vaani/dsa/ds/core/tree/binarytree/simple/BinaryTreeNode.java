package com.vaani.dsa.ds.core.tree.binarytree.simple;

public class BinaryTreeNode {
    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    BinaryTreeNode(int value) {
        this.value = value;
    }

    BinaryTreeNode(int val, BinaryTreeNode left, BinaryTreeNode right) {
        this.value = val;
        this.left = left;
        this.right = right;
    }
}
