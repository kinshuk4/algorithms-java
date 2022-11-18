package com.vaani.dsa.algo.ds.tree.binary;


import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import static com.vaani.dsa.algo.ds.tree.binary.IsSameTree_EqualTree.isSameTree;
import static com.vaani.dsa.algo.ds.tree.binary.IsSameTree_EqualTree.isSameTreeGeneric;

//https://app.codesignal.com/interview-practice/task/mDpAJnDQkJqaYYsCg
// https://leetcode.com/problems/subtree-of-another-tree/
/*
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2

Given tree t:

   4
  / \
 1   2

Return true, because t has the same structure and node values with a subtree of s.
 */
public class IsSubTree_SubtreeOfAnotherTree {

    // Time complexity - O(m*n), Space complexity - O(min(m, n));
    public boolean isSubtree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return false;
        } else if (isSameTree(t1, t2)) {
            return true;
        } else {
            return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
        }
    }


    static boolean isSubtreeGeneric(com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode<Integer> t1, com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode<Integer> t2) {
        if (t2 == null) {
            return true;
        } else if (t1 == null) {
            return false;
        }

        if (t1.val.equals(t2.val)) {
            return isSameTreeGeneric(t1, t2);
        }
        return isSubtreeGeneric(t1.left, t2) || isSubtreeGeneric(t1.right, t2);
    }


    //This logic will fail
    boolean isSubtree_Wrong(com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode<Integer> t1, com.vaani.dsa.ds.core.tree.binarytree.generic.BinaryTreeNode<Integer> t2) {
        if (t2 == null) {
            return true;
        } else if (t1 == null) {
            return false;
        }
        //Why it is wrong - when value is same, tree should match and not check for subtree
        if (t1.val.equals(t2.val)) {
            return isSubtree_Wrong(t1.left, t2.left) && isSubtree_Wrong(t1.right, t2.right);
        } else {
            return isSubtree_Wrong(t1.left, t2) || isSubtree_Wrong(t1.right, t2);
        }
    }
}
