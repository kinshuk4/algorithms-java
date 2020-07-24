package com.vaani.dsa.algo.ds.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/* https://leetcode.com/problems/kth-smallest-element-in-a-bst/
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.



Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */
public class KthSmallestInBST {
    public static int kthSmallestInBST(BinaryTreeNode root, int k) {
        return NthElementInorderTraversal.getNthIterative(root, k);
    }
    public int kthSmallestRecursive(BinaryTreeNode root, int k) {
        int[] nums = new int[2]; // 0th = where we are uptill k, 1th = value of the node that we have to return
        inorder(root, nums, k);
        return nums[1];
    }

    private void inorder(BinaryTreeNode root, int[] nums, int k) {
        if(root == null){
            return;
        }
        inorder(root.left, nums, k);
        if(++nums[0] == k){
            nums[1] = root.val;
            return;
        }
        inorder(root.right, nums, k);
    }

}
