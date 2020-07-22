package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

public class CloneBinaryTree {
    public static BinaryTreeNode cloneTree(BinaryTreeNode root) {
        if (root == null) {return null;}
        BinaryTreeNode newNode = new BinaryTreeNode(root.val);
        newNode.left = cloneTree(root.left);
        newNode.right = cloneTree(root.right);
        return newNode;
    }
}
