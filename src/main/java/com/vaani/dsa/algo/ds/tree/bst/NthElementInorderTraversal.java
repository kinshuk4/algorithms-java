package com.vaani.dsa.algo.ds.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.Stack;

/**
 * Write a function that takes 2 arguments: a binary tree and an integer n, it should return the n-th element in the inorder traversal of the binary tree
 */
public class NthElementInorderTraversal {
    int order = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);


        NthElementInorderTraversal underTest = new NthElementInorderTraversal();
        System.out.println(getNthIterative(root, 2));
        System.out.println(underTest.getNthRecursive(root, 2));
    }

    public static int getNthRecursive(TreeNode node, int n) {
        ValueHolder holder = new ValueHolder();
        holder.value = 0;
        return getNthRecursiveHelper(node, n, holder);
    }

    static class ValueHolder {
        int value;
    }

    public static int getNthRecursiveHelper(TreeNode node, int n, ValueHolder order) {
        if (node == null) {
            return -1;
        }
        int left = getNthRecursiveHelper(node.left, n, order);
        if (left != -1) {
            return left;
        }
        order.value += 1;
        if (order.value == n) {
            return node.val;
        }
        int right = getNthRecursiveHelper(node.right, n, order);
        return right;
    }

    // submitted
    public static int getNthIterative(TreeNode root, int n) {
        if (root == null) {
            return -1;
        }
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                n--;
                if (n == 0) {
                    return node.val;
                }
                root = node.right;
            }
        }
        return -1;
    }
}
