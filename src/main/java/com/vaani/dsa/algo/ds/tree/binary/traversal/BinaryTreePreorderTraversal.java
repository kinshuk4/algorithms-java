package com.vaani.dsa.algo.ds.tree.binary.traversal;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.ArrayList;
import java.util.List;
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

public class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    public void helper(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        helper(root.left, result);
        helper(root.right, result);
    }

    //iterative
    public List<Integer> preOrderTraversalIterative(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
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
