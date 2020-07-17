package com.vaani.dsa.ds.algos.convert;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;



/* https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
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

    public BinaryTreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    public BinaryTreeNode sortedArrayToBSTHelper(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        BinaryTreeNode root = new BinaryTreeNode(arr[mid]);
        root.left = sortedArrayToBSTHelper(arr, start, mid - 1);
        root.right = sortedArrayToBSTHelper(arr, mid + 1, end);
        return root;
    }
}