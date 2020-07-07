package com.vaani.dsa.ds.utils.simple;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

public class BinaryTreeUtil {

    /*
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
     */
    public static BinaryTreeNode getABinaryTree1() {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(4);
        root.right = new BinaryTreeNode(8);
        root.left.left = new BinaryTreeNode(11);
        root.right.left = new BinaryTreeNode(13);
        root.right.right = new BinaryTreeNode(4);
        root.left.left.left = new BinaryTreeNode(7);
        root.left.left.right = new BinaryTreeNode(2);
        root.right.right.right = new BinaryTreeNode(1);
        return root;
    }
}
