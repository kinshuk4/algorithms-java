package com.vaani.dsa.algo.ds.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

public class InsertintoaBinarySearchTree {
    public BinaryTreeNode insertIntoBST(BinaryTreeNode root, int val) {
        if (root == null) {
            return new BinaryTreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public BinaryTreeNode insertIntoBSTIterative(BinaryTreeNode root, int val) {
        if (root == null) {
            return new BinaryTreeNode(val);
        }
        BinaryTreeNode curr = root;
        BinaryTreeNode newNode = new BinaryTreeNode(val);
        while (true){
            if (curr.val > val) {
                if(curr.left == null){
                    curr.left = newNode;
                    break;
                }
                curr = curr.left;
            } else {
                if(curr.right == null){
                    curr.right = newNode;
                    break;
                }
                curr = curr.right;
            }

        }

        return root;
    }
}
