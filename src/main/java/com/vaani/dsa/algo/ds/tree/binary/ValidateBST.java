package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.TreeNode;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * on 8/9/2014.
 */
public class ValidateBST {

    boolean isValidBSTWrong(TreeNode node) {
        if (node == null) {
            return true;
        }

        // false if left is > than node
        if (node.left != null && node.left.val > node.val) {
            return false;
        }

        // false if right is < than node
        if (node.right != null && node.right.val < node.val) {
            return false;
        }

        // false if, recursively, the left or right is not a BST
        if (!isValidBSTWrong(node.left) || !isValidBSTWrong(node.right)) {
            return false;
        }

        // passing all that, it's a BST
        return true;
    }

    boolean isValidBSTInefficient(TreeNode node) {

        if (node == null)
            return (true);

        /* false if the max of the left is > than us */
        if (node.left != null && maxValue(node.left) > node.val)
            return (false);

        /* false if the min of the right is <= than us */
        if (node.right != null && minValue(node.right) < node.val)
            return (false);

        /* false if, recursively, the left or right is not a BST */
        if (!isValidBSTInefficient(node.left) || !isValidBSTInefficient(node.right))
            return (false);

        /* passing all that, it's a BST */
        return (true);

    }

    int minValue(TreeNode node) {
        TreeNode current = node;   // loop down to find the leftmost leaf
        while (current.left != null) {
            current = current.left;
        }
        return current.val;
    }

    int maxValue(TreeNode node) {
        TreeNode current = node;   // loop down to find the leftmost leaf
        while (current.right != null) {
            current = current.right;
        }
        return current.val;
    }

    public boolean isValidBSTEfficient(TreeNode root) {
        return isValidBSTEfficientHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // We are using long min and long max to handle the case
    // when node's value is Integer.MIN_VALUE OR Integer.MAX_VALUE
    public boolean isValidBSTEfficientHelper(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val > min && root.val < max
                && isValidBSTEfficientHelper(root.left, min, root.val)
                && isValidBSTEfficientHelper(root.right, root.val, max)) {
            return true;
        } else {
            return false;
        }
    }


    class BNode {
        TreeNode n;
        double left;
        double right;

        public BNode(TreeNode n, double left, double right) {
            this.n = n;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isValidBSTIterative(TreeNode root) {
        if (root == null)
            return true;

        LinkedList<BNode> queue = new LinkedList<>();
        queue.add(new BNode(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
        while (!queue.isEmpty()) {
            BNode b = queue.poll();
            if (b.n.val <= b.left || b.n.val >= b.right) {
                return false;
            }
            if (b.n.left != null) {
                queue.offer(new BNode(b.n.left, b.left, b.n.val));
            }
            if (b.n.right != null) {
                queue.offer(new BNode(b.n.right, b.n.val, b.right));
            }
        }
        return true;
    }

    TreeNode previous;
    boolean valid;

    public boolean isValidBSTInorder(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        previous = null;
        valid = true;
        isValidInorderHelper(root);
        return valid;
    }

    public void isValidInorderHelper(TreeNode root) {
        if (root == null) {
            return;
        }
        isValidInorderHelper(root.left);
        if (previous != null && previous.val >= root.val) {
            valid = false;
            return;
        }
        previous = root;
        isValidInorderHelper(root.right);
    }
}
