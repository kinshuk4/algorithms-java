package com.vaani.dsa.algo.ds.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

public class InsertintoaBinarySearchTree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    public TreeNode insertIntoBSTIterative(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode curr = root;
        TreeNode newNode = new TreeNode(val);
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
