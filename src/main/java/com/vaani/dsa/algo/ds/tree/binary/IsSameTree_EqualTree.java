package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;
/*
https://leetcode.com/problems/same-tree/
Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true
Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false
 */
public class IsSameTree_EqualTree {


    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);

        IsSameTree_EqualTree test = new IsSameTree_EqualTree();
        System.out.println(test.isSameTree(root1, root2));
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null)
            return false;
        else
            return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // more smaller
    public static boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null || q == null)
            return p == null && q == null;
        else
            return (p.val == q.val) && isSameTree2(p.left, q.left) || isSameTree2(p.right, q.right);
    }


    public static boolean isSameTreeGeneric(com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode<Integer> t1, com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode<Integer> t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return t1 == t2;
        }
        if (!t1.val.equals(t2.val)) {
            return false;
        }
        return isSameTreeGeneric(t1.left, t2.left) && isSameTreeGeneric(t1.right, t2.right);
    }
}
