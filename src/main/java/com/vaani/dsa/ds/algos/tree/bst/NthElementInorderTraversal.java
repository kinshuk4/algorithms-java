package com.vaani.dsa.ds.algos.tree.bst;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.Stack;

/**
 * Write a function that takes 2 arguments: a binary tree and an integer n, it should return the n-th element in the inorder traversal of the binary tree
 */
public class NthElementInorderTraversal {
    int order = 0;

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(5);
        root.left = new BinaryTreeNode(4);
        root.left.left = new BinaryTreeNode(2);
        root.left.right = new BinaryTreeNode(1);
        root.right = new BinaryTreeNode(3);
        root.right.right = new BinaryTreeNode(6);


        NthElementInorderTraversal underTest = new NthElementInorderTraversal();
        System.out.println(getNthIterative(root, 2));
        System.out.println(underTest.getNthRecursive(root, 2));
    }

    public static int getNthRecursive(BinaryTreeNode node, int n) {
        ValueHolder holder = new ValueHolder();
        holder.value = 0;
        return getNthRecursiveHelper(node, n, holder);
    }

    static class ValueHolder {
        int value;
    }

    public static int getNthRecursiveHelper(BinaryTreeNode node, int n, ValueHolder order) {
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

    public static int getNthIterative(BinaryTreeNode root, int n) {
        if (root == null) {
            return -1;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                BinaryTreeNode node = stack.pop();
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
