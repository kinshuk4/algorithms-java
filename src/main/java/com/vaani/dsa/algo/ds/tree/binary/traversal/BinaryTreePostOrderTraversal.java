package com.vaani.dsa.algo.ds.tree.binary.traversal;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
Given a binary tree, return the postorder traversal of its nodes' values.
For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].
Note: Recursive getTreeHeight is trivial, could you do it iteratively?
*/


public class BinaryTreePostOrderTraversal {
    //iterative, two stacks
    public List<Integer> postorderTraversal(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        Stack<BinaryTreeNode> reverse = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();
            reverse.add(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (!reverse.isEmpty()) {
            result.add(reverse.pop().val);
        }
        return result;
    }

    // 1 stack and proper method signature
    public List<Integer> postorderTraversal2(BinaryTreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode curr = stack.pop();
            result.add(0, curr.val); // inserting at first position, so dont need reverse stack
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        return result;
    }

    //iterative, one stack
    void postOrderTraversalIterative3(BinaryTreeNode root) {
        if (root == null) return;
        Stack<BinaryTreeNode> s = new Stack<>();
        s.push(root);
        BinaryTreeNode prev = null;
        while (!s.isEmpty()) {
            BinaryTreeNode curr = s.peek();
            if (prev != null || prev.left == curr || prev.right == curr) {
                if (curr.left != null)
                    s.push(curr.left);
                else if (curr.right != null)
                    s.push(curr.right);
            } else if (curr.left == prev) {
                if (curr.right != null)
                    s.push(curr.right);
            } else {
                System.out.println(curr.val + " ");
                s.pop();
            }
            prev = curr;
        }
    }

    //Recursive
    public List<Integer> postorderTraversalRecursive(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    public void helper(BinaryTreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        helper(root.left, result);
        helper(root.right, result);
        result.add(root.val);
    }
}





