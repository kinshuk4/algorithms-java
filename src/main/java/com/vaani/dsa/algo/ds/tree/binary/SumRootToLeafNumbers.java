package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * For example,
 */
public class SumRootToLeafNumbers {


    public static void main(String[] args) {
        BinaryTreeNode node = new BinaryTreeNode(1);
        node.left = new BinaryTreeNode(2);
        node.right = new BinaryTreeNode(3);

        SumRootToLeafNumbers.UsingDFS1 test = new UsingDFS1();
        System.out.println(test.sumNumbers(node));
    }

    static class UsingDFS1 {
        int sum = 0;

        public int sumNumbers(BinaryTreeNode root) {
            sumNumbers(root, 0);
            return sum;
        }

        public void sumNumbers(BinaryTreeNode root, int currSum) {
            if (root == null) {
                return;
            }
            currSum = 10 * currSum + root.val;
            if (root.left == null && root.right == null) {
                sum += currSum;
                return;
            }
            sumNumbers(root.left, currSum);
            sumNumbers(root.right, currSum);
        }
    }

    static class UsingDFS2 {
        int sum;

        public int sumNumbers(BinaryTreeNode root) {
            sum = 0;
            if (root == null) {
                return sum;
            }
            helper(root, 0);
            return sum;
        }

        public void helper(BinaryTreeNode root, int curSum) {
            curSum = curSum * 10 + root.val;
            if (root.left == null && root.right == null) {
                sum = sum + curSum;
            }
            if (root.left != null) {
                helper(root.left, curSum);
            }
            if (root.right != null) {
                helper(root.right, curSum);
            }
        }
    }


}
