package com.vaani.dsa.algo.ds.convert;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;



/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * <p>
 * 108. Convert Sorted Array to Binary Search Tree
 * Easy
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
/*
  Example
 * Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
 *
 */
public class ConvertSortedArrayToBST {
    public static void main(String[] args) {
        int[] num = {2, 4, 7, 9, 10, 12};
        ConvertSortedArrayToBST test = new ConvertSortedArrayToBST();
        test.sortedArrayToBST(num);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBSTHelper(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = sortedArrayToBSTHelper(arr, start, mid - 1);
        root.right = sortedArrayToBSTHelper(arr, mid + 1, end);
        return root;
    }
}