package com.vaani.dsa.ds.utils.simple;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

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
    public static TreeNode getABinaryTree1() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);
        return root;
    }
}
