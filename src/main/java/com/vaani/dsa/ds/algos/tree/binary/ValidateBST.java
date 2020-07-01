package com.vaani.dsa.ds.algos.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import java.util.*;

/**
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

    boolean isValidBSTWrong(BinaryTreeNode node) {
        if (node == null) {
            return true;
        }

        // false if left is > than node
        if (node.left != null && node.left.value > node.value) {
            return false;
        }

        // false if right is < than node
        if (node.right != null && node.right.value < node.value) {
            return false;
        }

        // false if, recursively, the left or right is not a BST
        if (!isValidBSTWrong(node.left) || !isValidBSTWrong(node.right)) {
            return false;
        }

        // passing all that, it's a BST
        return true;
    }

    boolean isValidBSTInefficient(BinaryTreeNode node) {

        if (node == null)
            return (true);

        /* false if the max of the left is > than us */
        if (node.left != null && maxValue(node.left) > node.value)
            return (false);

        /* false if the min of the right is <= than us */
        if (node.right != null && minValue(node.right) < node.value)
            return (false);

        /* false if, recursively, the left or right is not a BST */
        if (!isValidBSTInefficient(node.left) || !isValidBSTInefficient(node.right))
            return (false);

        /* passing all that, it's a BST */
        return (true);

    }

    int minValue(BinaryTreeNode node) {
        BinaryTreeNode current = node;   // loop down to find the leftmost leaf
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    int maxValue(BinaryTreeNode node) {
        BinaryTreeNode current = node;   // loop down to find the leftmost leaf
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public boolean isValidBST(BinaryTreeNode root) {
        return isValidBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValidBSTHelper(BinaryTreeNode root, int min, int max) {
        if (root == null) return true;
        if (root.value > min && root.value < max
                && isValidBSTHelper(root.left, min, root.value)
                && isValidBSTHelper(root.right, root.value, max)) {
            return true;
        } else {
            return false;
        }
    }


    class BNode {
        BinaryTreeNode n;
        double left;
        double right;

        public BNode(BinaryTreeNode n, double left, double right) {
            this.n = n;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isValidBSTIterative(BinaryTreeNode root) {
        if (root == null)
            return true;

        LinkedList<BNode> queue = new LinkedList<>();
        queue.add(new BNode(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
        while (!queue.isEmpty()) {
            BNode b = queue.poll();
            if (b.n.value <= b.left || b.n.value >= b.right) {
                return false;
            }
            if (b.n.left != null) {
                queue.offer(new BNode(b.n.left, b.left, b.n.value));
            }
            if (b.n.right != null) {
                queue.offer(new BNode(b.n.right, b.n.value, b.right));
            }
        }
        return true;
    }

    BinaryTreeNode previous;
    boolean valid;

    public boolean isValidBSTInorder(BinaryTreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        previous = null;
        valid = true;
        isValidInorderHelper(root);
        return valid;
    }

    public void isValidInorderHelper(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        isValidInorderHelper(root.left);
        if (previous != null && previous.value >= root.value) {
            valid = false;
            return;
        }
        previous = root;
        isValidInorderHelper(root.right);
    }
}
