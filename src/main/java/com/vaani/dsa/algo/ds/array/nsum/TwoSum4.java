package com.vaani.dsa.algo.ds.array.nsum;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 * 653. Two Sum IV - Input is a BST
 * Easy
 * <p>
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.
 */
/*
Example 1:

Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True



Example 2:

Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
 */
public class TwoSum4 {

    public static void main(String[] args) throws Exception {
    }

    public boolean findTarget(BinaryTreeNode root, int k) {
        return inorder(root, new HashSet<>(), k);
    }

    private boolean inorder(BinaryTreeNode root, HashSet<Integer> set, int k) {
        if (root != null) {
            int req = k - (root.val);
            if (set.contains(req)) {
                return true;
            }
            set.add(root.val);
            if (inorder(root.left, set, k)) {
                return true;
            } else {
                return inorder(root.right, set, k);
            }
        }
        return false;
    }
}
