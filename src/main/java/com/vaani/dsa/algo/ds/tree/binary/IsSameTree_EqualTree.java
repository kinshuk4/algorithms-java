package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;
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
        BinaryTreeNode root1 = new BinaryTreeNode(1);
        root1.left = new BinaryTreeNode(2);
        root1.right = new BinaryTreeNode(3);

        BinaryTreeNode root2 = new BinaryTreeNode(1);
        root2.left = new BinaryTreeNode(2);
        root2.right = new BinaryTreeNode(3);

        IsSameTree_EqualTree test = new IsSameTree_EqualTree();
        System.out.println(test.isSameTree(root1, root2));
    }

    public boolean isSameTree(BinaryTreeNode p, BinaryTreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null)
            return false;
        else
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
