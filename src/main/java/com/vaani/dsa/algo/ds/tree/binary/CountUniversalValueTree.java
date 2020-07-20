package com.vaani.dsa.algo.ds.tree.binary;

import com.vaani.dsa.ds.core.tree.binarytree.simple.BinaryTreeNode;

import static com.vaani.dsa.algo.ds.tree.binary.IsUniValuedBinaryTree.isUnivalTree;

public class CountUniversalValueTree {

    // O (n ^ 2)
    int countUniValTreeBad(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        int totalCount = countUniValTreeBad(root.left) + countUniValTreeBad(root.right);
        if (isUnivalTree(root)) {
            totalCount += 1;
        }
        return totalCount;
    }

    static class Counter {
        int count = 0;
    }


    // This function increments count by number of single
    // valued subtrees under root. It returns true if subtree
    // under root is Singly, else false.
    // https://www.youtube.com/watch?v=7HgsS8bRvjo
    boolean countUniValTreeHelper(BinaryTreeNode root, Counter c) {
        // Return false to indicate NULL
        if (root == null) {
            return true;
        }

        // Recursively count in left and right subtrees also
        boolean left = countUniValTreeHelper(root.left, c);
        boolean right = countUniValTreeHelper(root.right, c);

        // If any of the subtrees is not singly, then this
        // cannot be singly.
        if (!left || !right) {
            return false;
        }

        // If left subtree is singly and non-empty, but data
        // doesn't match
        if (root.left != null && !(root.val == root.left.val)) {
            return false;
        }


        // Same for right subtree
        if (root.right != null && !(root.val == root.right.val)) {
            return false;
        }

        // If none of the above conditions is true, then
        // tree rooted under root is single valued, increment
        // count and return true.
        c.count++;
        return true;
    }

    int countUnivalTree(BinaryTreeNode root) {
        Counter ct = new Counter();
        // Recursive function to count
        countUniValTreeHelper(root, ct);
        return ct.count;
    }
}
