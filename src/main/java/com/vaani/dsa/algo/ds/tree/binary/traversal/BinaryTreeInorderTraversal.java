package com.vaani.dsa.algo.ds.tree.binary.traversal;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// @formatter:off

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * Given a binary tree, return the inorder traversal of its nodes' values.
 */
/*
For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].
Note: Recursive getTreeHeight is trivial, could you do it iteratively?
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
*/

// @formatter:on

public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> ans = new BinaryTreeInorderTraversal.UsingIterative2().inorderTraversal(root);
    }
    //iterative
    static class UsingIterative1 {
        public static List<Integer> inorderTraversalIterative(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();

            TreeNode node = root;
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();
                    result.add(node.val);
                    node = node.right;
                }
            }
            return result;
        }
    }

    static class UsingIterative2 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();

            if (root == null) return list;

            Stack<TreeNode> stack = new Stack<>();
            //define a pointer to track nodes
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode top = stack.peek();
                System.out.println(top.val);
                while (top.left != null) {
                    top = top.left;
                    System.out.println(top.val);
                    stack.push(top);
                }

                top = stack.pop();
                list.add(top.val);
                while (top.right == null) {
                    top = stack.pop();
                    list.add(top.val);
                }
                if(top != null && top.right != null) {
                    stack.push(top.right);
                }
            }

            return list;
        }
    }

    //recursive
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRecursiveHelper(root, result);
        return result;
    }

    public void inorderTraversalRecursiveHelper(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorderTraversalRecursiveHelper(root.left, result);
        result.add(root.val);
        inorderTraversalRecursiveHelper(root.right, result);
    }
}
