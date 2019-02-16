package com.vaani.algo.ds.algos.tree.binary.traversal;

import com.vaani.algo.ds.core.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Stack;

/*
Given a binary tree, return the preorder traversal of its nodes' values.
For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].
Note: Recursive getTreeHeight is trivial, could you do it iteratively?
*/

//recursive

/**
 * Definition for binary tree
 * public class BinaryTreeNode {
 * int val;
 * BinaryTreeNode left;
 * BinaryTreeNode right;
 * BinaryTreeNode(int x) { val = x; }
 * }
 */
public class PreorderTraversal {
    public ArrayList<Integer> preorderTraversal(BinaryTreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> result = new ArrayList<Integer>();
        helper(root, result);
        return result;
    }

    public void helper(BinaryTreeNode<Integer> root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        helper(root.left, result);
        helper(root.right, result);
    }

    //iterative
    public ArrayList<Integer> preorderTraversalIter(BinaryTreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode<Integer> node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }
}
